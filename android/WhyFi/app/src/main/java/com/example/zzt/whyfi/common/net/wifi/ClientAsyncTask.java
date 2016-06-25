package com.example.zzt.whyfi.common.net.wifi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
public class ClientAsyncTask extends AsyncTask<Void, Void, Void> {

    public static final String CANONICAL_NAME = ClientAsyncTask.class.getCanonicalName();
    private WiFiDirectBroadcastReceiver receiver;
    private InetSocketAddress inetSocketAddress;

    public ClientAsyncTask(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    public ClientAsyncTask(String host) {
        this(host, WifiSetting.DEFAULT_PORT);
    }

    public ClientAsyncTask(WiFiDirectBroadcastReceiver receiver, String hostAddress) {
        this(hostAddress);
        this.receiver = receiver;
    }


    @Override
    protected Void doInBackground(Void... params) {
        Socket socket = new Socket();
        try {
            /**
             * Create a client socket with the host,
             * port, and timeout information.
             */
            Log.d(CANONICAL_NAME, "client started");
            socket.bind(null);
            socket.connect(inetSocketAddress, 5000);

            receiver.setState(ConnectionState.CLIENT_CONNECTED);
            // notice here we do not run it in another thread,
            // for this is already in a worker thread
            Log.d(CANONICAL_NAME, "client connected");
            new ConnectedJob(socket, false).run();
            receiver.setClientStarted(false);
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, "error connect", e);
            receiver.resetClient();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e(CANONICAL_NAME, "error close client", e);
                receiver.resetClient();
            }
        }
        return null;
    }
}
