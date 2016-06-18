package com.example.zzt.whyfi.common.net.BT;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by zzt on 6/10/16.
 * <p>
 * Usage:
 */
public class ConnectedBT implements Runnable {
    private static final String CANONICAL_NAME = ConnectedBT.class.getCanonicalName();
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public ConnectedBT(BluetoothSocket socket) {
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
    }

    public void read() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.e(CANONICAL_NAME, "should not be main looper", new Exception());
        }

        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (BlueToothMsg.isConnected()) {
            try {
                // Read from the InputStream
                Log.d(CANONICAL_NAME, "trying reading");
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                final byte[] copyOf = Arrays.copyOf(buffer, bytes);
                Log.d(CANONICAL_NAME, "update received message");
                try {
                    MsgHistory.addReceived(Message.getFromBytes(copyOf));
                } catch (UnsupportedEncodingException e) {
                    Log.e(CANONICAL_NAME, "Exception during message conversion", e);
                }
                Log.d(CANONICAL_NAME, new String(copyOf));
            } catch (IOException e) {
                cancel();
                Log.e(CANONICAL_NAME, "Exception during read", e);
                break;
            }
        }
    }

    /* Call this from the main activity to send data to the remote device */
    public void writeln(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
            mmOutStream.flush();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during write", e);
        }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during close", e);
        }
    }

    @Override
    public void run() {
        BTMsgWriter.performWrite(this);
        read();
    }
}
