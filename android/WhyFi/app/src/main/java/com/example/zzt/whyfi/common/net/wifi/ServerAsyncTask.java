package com.example.zzt.whyfi.common.net.wifi;

import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.util.Log;

import net.jcip.annotations.GuardedBy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 6/18/16.
 * <p>
 * Usage:
 */
public class ServerAsyncTask extends AsyncTask<Void, Void, String> {

    public static final String CANONICAL_NAME = ServerAsyncTask.class.getCanonicalName();
    private static final ExecutorService service = Executors.newCachedThreadPool();
    private volatile boolean cancel = false;
    private final ServerSocket serverSocket;


    public ServerAsyncTask() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(WifiSetting.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serverSocket = serverSocket;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            List<ConnectedJob> list = new ArrayList<>();
            while (!cancel) {
                Socket client = serverSocket.accept();

                Log.d(CANONICAL_NAME, "server connected");
                ConnectedJob command = new ConnectedJob(client, true);
                list.add(command);
                service.execute(command);
            }
            for (ConnectedJob connectedJob : list) {
                connectedJob.cancel();
            }
            service.shutdownNow();
        } catch (IOException e) {
            Log.e(CANONICAL_NAME, e.getMessage());
        }
        return null;
    }

    @UiThread
    @GuardedBy("volatile")
    public void stopListen() {
        cancel = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

