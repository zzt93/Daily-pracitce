package com.example.zzt.whyfi.common.net.wifi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by zzt on 6/18/16.
 * <p>
 * Usage:
 */
public class ServerAsyncTask extends AsyncTask<Void, Void, String> {

    public static final String CANONICAL_NAME = ServerAsyncTask.class.getCanonicalName();
    private Context context;
    private TextView statusText;

    public ServerAsyncTask(Context context, View statusText) {
        this.context = context;
        this.statusText = (TextView) statusText;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket client = serverSocket.accept();

            /**
             * If this code is reached, a client has connected and transferred data
             * Save the input stream from the client as a JPEG file
             */
            InputStream inputstream = client.getInputStream();
            String msg = readMsg(inputstream);
            serverSocket.close();
            return msg;
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, e.getMessage());
            return null;
        }
    }

    private String readMsg(InputStream inputstream) {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int len; // len returned from read()

        // Read from the InputStream
        Log.d(CANONICAL_NAME, "trying reading");
        try {

            while ((len = inputstream.read(buffer)) != -1) {

            }
            // Send the obtained len to the UI activity
            final byte[] copyOf = Arrays.copyOf(buffer, len);
            Log.d(CANONICAL_NAME, "update received message");
            try {
                MsgHistory.addReceived(Message.getFromBytes(copyOf));
            } catch (UnsupportedEncodingException e) {
                Log.e(CANONICAL_NAME, "Exception during message conversion", e);
            }
            Log.d(CANONICAL_NAME, new String(copyOf));
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "Exception during read", e);
        }
        return null;
    }


    /**
     * Start activity that can handle the JPEG image
     */
    @Override
    protected void onPostExecute(String result) {
    }

}

