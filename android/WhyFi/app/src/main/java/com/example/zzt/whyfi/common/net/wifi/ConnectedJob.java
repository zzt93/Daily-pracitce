package com.example.zzt.whyfi.common.net.wifi;

import android.os.Looper;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.example.zzt.whyfi.common.net.ConnectedChannel;
import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
@WorkerThread
public class ConnectedJob implements Runnable, ConnectedChannel {
    private static final String CANONICAL_NAME = ConnectedJob.class.getCanonicalName();
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private final Socket mmSocket;
    private OutputStreamWriter out;
    private BufferedReader reader;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private MsgHistory msgHistory = new MsgHistory();

    public ConnectedJob(Socket socket, boolean server) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "disconnected", e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        try {
//            out = new OutputStreamWriter(mmOutStream, BytesSetting.UTF_8);
//            reader = new BufferedReader(new InputStreamReader(
//                    socket.getInputStream(), "UTF-8"));
            objectOutputStream = new ObjectOutputStream(mmOutStream);
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error create ", e);
        }
    }

    @Override
    @WorkerThread
    public void read() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.e(CANONICAL_NAME, "should not be main looper", new Exception());
        }

        byte[] buffer = new byte[1024];  // buffer store for the stream
        int len; // len returned from read()

        // Keep listening to the InputStream until an exception occurs
        try {
            // Read from the InputStream
            len = mmInStream.read(buffer);
            if (len == -1) {
                return;
            }
            // Send the obtained len to the UI activity
            final byte[] copyOf = Arrays.copyOf(buffer, len);
            Log.d(CANONICAL_NAME, "update received message");
            try {
                MsgHistory.addReceived(Message.getFromBytes(copyOf));
            } catch (UnsupportedEncodingException e) {
                Log.e(CANONICAL_NAME, "Exception during message conversion", e);
            }
            Log.d(CANONICAL_NAME, new String(copyOf, "UTF-8"));
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during read", e);
        }
    }


    @WorkerThread
    @Override
    public void writeByte(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
            mmOutStream.flush();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during write", e);
        }
    }

    @Override
    @WorkerThread
    public void readStr() {
        char[] buffer = new char[1024];  // buffer store for the stream
        int len; // len returned from read()

        // Keep listening to the InputStream until an exception occurs
        try {
            // Read from the InputStream
            len = reader.read(buffer);

            if (len == -1) {
                return;
            }
            // Send the obtained len to the UI activity
            final char[] copyOf = Arrays.copyOf(buffer, len);
            Log.d(CANONICAL_NAME, "update received message");
            try {
                MsgHistory.addReceived(Message.getFromChars(copyOf));
            } catch (UnsupportedEncodingException e) {
                Log.e(CANONICAL_NAME, "Exception during message conversion", e);
            }
            Log.d(CANONICAL_NAME, new String(copyOf));
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during read", e);
        }
    }

    @Override
    public void readMsg() {
//        try {
//            Message message = (Message) objectInputStream.readObject();
//            MsgHistory.addReceived(message);
//        } catch (ClassNotFoundException | IOException e) {
//            Log.e(CANONICAL_NAME, "Exception during read", e);
//        }
    }

    @Override
    public void readList() {
        try {
            List<Message> message = (List<Message>) objectInputStream.readObject();
            MsgHistory.addReceived(message);
        } catch (ClassNotFoundException | IOException e) {
            Log.e(CANONICAL_NAME, "Exception during read", e);
        }
    }

    @Override
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during close", e);
        }
    }

    @WorkerThread
    @Override
    public void write(String message) {
        try {
            out.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean write(Message message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error write", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean write(List<Message> messages) {
        try {
            objectOutputStream.writeObject(messages);
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error write", e);
            return false;
        }
        return true;
    }


    @WorkerThread
    @Override
    public void run() {
        Log.d(CANONICAL_NAME, "before write");
        try {
            msgHistory.performWrite(this);
            //  which cause StreamCorruptedException
            /**
             * can't close OOS before init OIS??? java.net.SocketException: recvfrom failed: EBADF (Bad file number)
             */
            objectOutputStream.flush();

            Log.d(CANONICAL_NAME, "before read");
            /**
             *  init OIS is blocking!!!
             */
            /**
             *  EOFException when before init OOS
             * -- reason:
             * The most likely cause is that the remote client did not open an ObjectOutputStream.
             * It might have written some other kind of data,
             * or it might have closed its output stream or simply exited.
             */
            objectInputStream = new ObjectInputStream(mmInStream);

            readList();
            objectOutputStream.close();
            objectInputStream.close();
            mmSocket.close();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error ", e);
        }
    }


}
