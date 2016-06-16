package com.example.zzt.whyfi.common.net;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;

/**
 * Created by zzt on 6/10/16.
 * <p/>
 * Usage:
 */
public class ServerJob implements Runnable {
    private static final String NAME = "ServerWriteChat";
    /**
     * You should usually close your BluetoothServerSocket
     * as soon as you are done listening for incoming connections.
     * In this example, close() is called as soon as the BluetoothSocket is acquired.
     */
    private final BluetoothServerSocket mmServerSocket;

    public ServerJob(BluetoothAdapter mBluetoothAdapter) {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, BlueToothMsg.BLE_CHAT_UUID);
        } catch (IOException e) {
            Log.e(NAME, "listen() failed", e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e(NAME, "accept() failed", e);
            }
            // If a connection was accepted
            if (socket != null) {
                BlueToothMsg.serverJob(mmServerSocket, socket);
            }
        }

    }

    /**
     * Will cancel the listening socket, and cause the thread to finish
     */
    public void cancel() {
        Log.d(NAME, "cancel " + this);
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(NAME, "close() of server failed", e);
        }
    }
}

