package com.example.zzt.whyfi.common.net.wifi;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
public abstract class WifiActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener{

    public abstract void setReceiver(WiFiDirectBroadcastReceiver receiver);

    public abstract WiFiDirectBroadcastReceiver getReceiver();

    private boolean retryChannel = false;

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

    @Override
    public void onChannelDisconnected() {
        WifiP2pManager manager = getReceiver().getmManager();
        // we will try once more
        if (manager != null && !retryChannel) {
            Toast.makeText(this, "Channel lost. Trying again", Toast.LENGTH_LONG).show();
//            resetData();
            retryChannel = true;
            manager.initialize(this, getMainLooper(), this);
        } else {
            Toast.makeText(this,
                    "Severe! Channel is probably lost premanently. Try Disable/Re-Enable P2P.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
