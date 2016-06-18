package com.example.zzt.whyfi.common;

import android.app.Activity;
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

    public Network(Set<String> names) {
    }

    public static boolean enableDiscoverable(Activity activity) {

        if (!activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(activity, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            return false;
        }

//        BlueToothMsg.init(activity);
//        BlueToothMsg.enableBT();
        return true;
    }



}
