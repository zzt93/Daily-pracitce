package com.example.zzt.whyfi.vm;

import android.app.Activity;

import com.polidea.rxandroidble.RxBleClient;

/**
 * Created by zzt on 6/5/16.
 * <p/>
 * Usage:
 */
public class BLE {

    public static RxBleClient getInstance(Activity activity) {
        if (rxBleClient == null) {
            rxBleClient = RxBleClient.create(activity);
        }
        return rxBleClient;
    }

    private static RxBleClient rxBleClient;

}