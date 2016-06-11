package com.example.zzt.whyfi.common;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.widget.Toast;

import com.example.zzt.whyfi.common.base.BTMsgWriter;
import com.example.zzt.whyfi.common.base.ClientJob;
import com.example.zzt.whyfi.common.base.ConnectedBT;
import com.example.zzt.whyfi.common.base.ServerJob;
import com.example.zzt.whyfi.common.base.ToGuard;

import net.jcip.annotations.GuardedBy;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 6/10/16.
 * <p>
 * Usage:
 */
public class BlueToothMsg {

    private static final String CANONICAL_NAME = BlueToothMsg.class.getCanonicalName();
    public static UUID BLE_CHAT_UUID =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");

    private static BluetoothAdapter mBluetoothAdapter;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static Context context;
    private static ClientJob client;

    private static int mState;
    // Create a BroadcastReceiver for ACTION_FOUND
    private static final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "find: " + device, Toast.LENGTH_SHORT).show();
                stopScan();
                connect(device);
            }
        }
    };


    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device


    @ToGuard(values = {"mState", "client", "mBluetoothAdapter"})
    @WorkerThread
    private static synchronized void connect(BluetoothDevice device) {
        setMState(STATE_CONNECTING);

        client = new ClientJob(device, mBluetoothAdapter);
        executorService.execute(client);
    }

    private static synchronized void runServer() {
        ServerJob server = new ServerJob(mBluetoothAdapter);
        executorService.execute(server);
    }

    @GuardedBy("thisClass")
    @ToGuard(values = {"mBluetoothAdapter", "context"})
    @UiThread
    public static void init(final Context context) {

        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

        synchronized (BlueToothMsg.class) {
            BlueToothMsg.context = context;
            setMState(STATE_NONE);
            mBluetoothAdapter = bluetoothManager.getAdapter();
            assert mBluetoothAdapter != null;
        }
        runServer();


        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.getApplicationContext().registerReceiver(mReceiver, filter);
    }

    @ToGuard("mBluetoothAdapter")
    @WorkerThread
    public static synchronized void start() {
        setMState(STATE_LISTEN);

        if (!mBluetoothAdapter.startDiscovery()) {
            Log.d(CANONICAL_NAME, "can't start scan");
        }
        for (BluetoothDevice device : mBluetoothAdapter.getBondedDevices()) {
            connect(device);
        }
    }

    @WorkerThread
    public static synchronized void stopConnection() {
        client.cancel();
//        server.cancel();
    }

    @ToGuard(values = {"mBluetoothAdapter", "context"})
    @WorkerThread
    private static synchronized void stopScan() {
        mBluetoothAdapter.cancelDiscovery();
        context.getApplicationContext().unregisterReceiver(mReceiver);
    }


    @UiThread
    public static void enableBT() {
        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent discoverableIntent = new
                    Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            context.startActivity(discoverableIntent);
        }
    }


    @ToGuard("mState")
    @WorkerThread
    public static synchronized boolean isConnected() {
        return mState == STATE_CONNECTED;
    }


    @ToGuard("mState")
    @WorkerThread
    public static synchronized void serverJob(BluetoothServerSocket mmServerSocket, BluetoothSocket socket) {
        switch (mState) {
            case STATE_LISTEN:
            case STATE_CONNECTING:
                // Do work to manage the connection (in a separate thread)
                BlueToothMsg.setConnected();
                if (client != null) {
                    client.cancel();
                }
                ConnectedBT connectedBT = new ConnectedBT(socket);
                executorService.execute(connectedBT);

                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            case STATE_NONE:
            case STATE_CONNECTED:
                // Either not ready or already connected. Terminate new socket.
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    Log.e(CANONICAL_NAME, "Could not close unwanted socket", e);
//                }
                break;
        }
    }

    @WorkerThread
    public static void clientJob(BluetoothSocket mmSocket) {
        setConnected();

        ConnectedBT connectedBT = new ConnectedBT(mmSocket);
        BTMsgWriter.performWrite(connectedBT);
        connectedBT.read();
    }

    @UiThread
    @WorkerThread
    @GuardedBy("this")
    public static synchronized void setMState(int state) {
        Log.d(CANONICAL_NAME, "setMState() " + mState + " -> " + state);

        BlueToothMsg.mState = state;
    }

    public static void setConnected() {
        setMState(STATE_CONNECTED);
    }


}
