package com.example.zzt.whyfi.common.base;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import com.example.zzt.whyfi.common.BlueToothMsg;

import java.io.IOException;

/**
 * Created by zzt on 6/10/16.
 * <p/>
 * Usage:
 */
public class ServerWrite implements Runnable {
    private static final String NAME = "chat";
    /**
     * You should usually close your BluetoothServerSocket
     * as soon as you are done listening for incoming connections.
     * In this example, close() is called as soon as the BluetoothSocket is acquired.
     */
    private final BluetoothServerSocket mmServerSocket;

    public ServerWrite(BluetoothAdapter mBluetoothAdapter) {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, BlueToothMsg.BLE_CHAT_UUID);
        } catch (IOException e) {
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
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)
                BlueToothMsg.write(new ConnectedBT(socket));
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * Will cancel the listening socket, and cause the thread to finish
     */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
        }
    }
}

