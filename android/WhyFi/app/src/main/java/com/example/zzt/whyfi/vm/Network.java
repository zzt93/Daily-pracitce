package com.example.zzt.whyfi.vm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import com.example.zzt.whyfi.R;

import java.util.Set;

/**
 * Created by zzt on 6/2/16.
 * <p/>
 * Usage:
 */
public class Network {
    private static final int REQUEST_ENABLE_BT = 1;
    private Set<String> names;

    public Network(Set<String> names) {
        this.names = names;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void enableBluetooth(Activity activity) {

        if (!activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(activity, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
        }
        BluetoothAdapter mBluetoothAdapter;

        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        queryPaired(mBluetoothAdapter);
    }

    private void queryPaired(BluetoothAdapter mBluetoothAdapter) {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                names.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }
}
