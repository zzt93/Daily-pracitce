package com.example.zzt.whyfi.common;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.example.zzt.whyfi.common.base.ClientRead;
import com.example.zzt.whyfi.common.base.ConnectedBT;
import com.example.zzt.whyfi.common.base.ServerWrite;
import com.example.zzt.whyfi.model.Message;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 6/10/16.
 * <p/>
 * Usage:
 */
public class BlueToothMsg {

    private static final String CANONICAL_NAME = BlueToothMsg.class.getCanonicalName();
    public static UUID BLE_CHAT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static BluetoothAdapter mBluetoothAdapter;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static Context context;

    // Create a BroadcastReceiver for ACTION_FOUND
    private static final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "find: " + device, Toast.LENGTH_SHORT).show();
                synchronized (BlueToothMsg.class) {
                    stopScan();
                    runServer();
                    runClient(device);
                }
            }
        }
    };
    private static ClientRead client;
    private static ServerWrite server;

    private static void runClient(BluetoothDevice device) {
        client = new ClientRead(device, mBluetoothAdapter);
        executorService.execute(
                client);
    }

    private static void runServer() {
        server = new ServerWrite(mBluetoothAdapter);
        executorService.execute(
                server);
    }

    public static synchronized void init(final Context context) {
        BlueToothMsg.context = context;
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.getApplicationContext().registerReceiver(mReceiver, filter);
    }

    public static synchronized void start() {
        if (!mBluetoothAdapter.startDiscovery()) {
            Log.d(CANONICAL_NAME, "can't start scan");
        }
    }

    public static synchronized void stopConnection() {
        client.cancel();
        server.cancel();
    }

    private static void stopScan() {
        mBluetoothAdapter.cancelDiscovery();
        context.getApplicationContext().unregisterReceiver(mReceiver);
    }


    private static ArrayList<byte[]> messages = new ArrayList<>();

    /**
     * Invoke from UI thread
     *
     * @param message message
     */
    public static synchronized void writeMsg(Message message) {
        try {
            messages.add(message.toBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

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

    public static synchronized void write(ConnectedBT connectedBT) {
        Toast.makeText(context, "write", Toast.LENGTH_SHORT).show();
        for (byte[] message : messages) {
            connectedBT.writeln(message);
        }
        messages.clear();
    }
}
