package com.example.zzt.whyfi.common.base;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.zzt.whyfi.common.BlueToothMsg;

import java.io.IOException;

/**
 * Created by zzt on 6/10/16.
 * <p/>
 * Usage:
 */
public class ClientJob implements Runnable {
    private static final String TAG = ClientJob.class.getCanonicalName();
    private final BluetoothSocket mmSocket;
    private BluetoothAdapter mBluetoothAdapter;

    public ClientJob(BluetoothDevice device, BluetoothAdapter mBluetoothAdapter) {
        this.mBluetoothAdapter = mBluetoothAdapter;
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(BlueToothMsg.BLE_CHAT_UUID);
        } catch (IOException e) {
            Log.e(TAG, "Socket Type: create() failed", e);
        }
        mmSocket = tmp;
    }

    public void run() {
        // Cancel discovery because it will slow down the connection
        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "unable to close() socket during connection failure", closeException);
            }
            return;
        }

        BlueToothMsg.clientJob(mmSocket);

    }

    /**
     * Will cancel an in-progress connection, and close the socket
     */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "close() of connect socket failed", e);
        }
    }
}
