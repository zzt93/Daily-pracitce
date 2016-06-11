package com.example.zzt.whyfi.common;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.example.zzt.whyfi.R;

import java.util.Set;

/**
 * Created by zzt on 6/2/16.
 * <p/>
 * Usage:
 */
public class Network {
    private Set<String> names;

    public Network(Set<String> names) {
        this.names = names;
    }

    public static boolean enableDiscoverable(Activity activity) {

        if (!activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(activity, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            return false;
        }

        BlueToothMsg.init(activity);
        BlueToothMsg.enableBT();
        return true;
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
