package com.example.zzt.whyfi.common;

import android.app.Application;
import android.content.Context;
import android.support.annotation.UiThread;

import com.example.zzt.whyfi.common.net.ThreadConfinement;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by zzt on 6/18/16.
 * <p>
 * Usage:
 */
@UiThread
@ThreadSafe
@ThreadConfinement
public class App extends Application {
    private static Context mContext;

    public void onCreate(){
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
