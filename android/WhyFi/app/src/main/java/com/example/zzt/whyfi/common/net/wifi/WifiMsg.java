package com.example.zzt.whyfi.common.net.wifi;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by zzt on 6/16/16.
 * <p>
 * Usage:
 */
public class WifiMsg {

    public static boolean setUpWifi(WifiActivity activity) {

        WifiP2pManager mManager = (WifiP2pManager) activity.getSystemService(Context.WIFI_P2P_SERVICE);
        WifiP2pManager.Channel mChannel = mManager.initialize(activity, activity.getMainLooper(), null);

        activity.setReceiver(new WiFiDirectBroadcastReceiver(mManager, mChannel, activity));
        return true;
    }
}
