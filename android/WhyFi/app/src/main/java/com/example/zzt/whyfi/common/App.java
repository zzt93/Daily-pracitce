package com.example.zzt.whyfi.common;

import android.app.Application;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.support.annotation.UiThread;
import android.util.Log;

import net.jcip.annotations.ThreadSafe;

import java.util.Collection;

/**
 * Created by zzt on 6/18/16.
 * <p>
 * Usage:
 */
@ThreadConfinement
@UiThread
@ThreadSafe
public class App extends Application {
    private static Context mContext;
    private WifiP2pDeviceList list = new WifiP2pDeviceList();


    public void onCreate(){
        super.onCreate();
        mContext = this;
        Log.d(App.class.getCanonicalName(), "created app");
    }

    @Override
    public void onTerminate() {
        Log.d(App.class.getCanonicalName(), "terminate app");
        super.onTerminate();
    }

    public static Context getContext(){
        return mContext;
    }

    public Collection<WifiP2pDevice> getList() {
        return list.getDeviceList();
    }

    public void setList(WifiP2pDeviceList list) {
        this.list = list;
    }
}
