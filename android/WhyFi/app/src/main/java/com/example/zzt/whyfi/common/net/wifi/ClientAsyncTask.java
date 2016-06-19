package com.example.zzt.whyfi.common.net.wifi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zzt on 6/19/16.
 * <p>
 * Usage:
 */
public class ClientAsyncTask extends AsyncTask<Void, Void, Void> {

    public static final String CANONICAL_NAME = ClientAsyncTask.class.getCanonicalName();
    private InetSocketAddress inetSocketAddress;

    public ClientAsyncTask(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    public ClientAsyncTask(String host) {
        int port = WifiSetting.DEFAULT;
        inetSocketAddress = new InetSocketAddress(host, port);
    }


    @Override
    protected Void doInBackground(Void... params) {
        Socket socket = new Socket();
        try {
            /**
             * Create a client socket with the host,
             * port, and timeout information.
             */
            socket.bind(null);
            socket.connect(inetSocketAddress, 500);

            // notice here we not run it in another thread,
            // for this is already in a worker thread
            Log.d(CANONICAL_NAME, "client connected");
            new ConnectedJob(socket, false).run();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error connect", e);
            //catch logic
        } finally {
            if (socket.isConnected()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    //catch logic
                }
            }
        }
        return null;
    }
}
