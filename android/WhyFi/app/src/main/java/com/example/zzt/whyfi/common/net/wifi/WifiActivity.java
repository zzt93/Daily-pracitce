package com.example.zzt.whyfi.common.net.wifi;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
public abstract class WifiActivity extends AppCompatActivity {

    public abstract void setReceiver(WiFiDirectBroadcastReceiver receiver);

    public abstract WiFiDirectBroadcastReceiver getReceiver();


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(getReceiver(), WiFiDirectBroadcastReceiver.mIntentFilter);
        getReceiver().discover();
    }

    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        unregisterReceiver(getReceiver());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        getReceiver().cleanUp();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
