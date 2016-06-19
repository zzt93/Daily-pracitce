package com.example.zzt.whyfi.common.net.wifi;

import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

/**
 * Created by zzt on 6/19/16.
 * <p>
 * Usage:
 */
public class ConnectAllPeer implements WifiP2pManager.PeerListListener {

    private static final String CANONICAL_NAME = ConnectAllPeer.class.getCanonicalName();
    private final WifiP2pManager mManager;
    private final WifiP2pManager.Channel mChannel;

    public ConnectAllPeer(WifiP2pManager mManager, WifiP2pManager.Channel mChannel) {
        this.mManager = mManager;
        this.mChannel = mChannel;
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        for (final WifiP2pDevice device : peers.getDeviceList()) {
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = device.deviceAddress;
            mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    //success logic
                    Log.d(CANONICAL_NAME, "succeed to connect peer: " + device.deviceAddress);
                }

                @Override
                public void onFailure(int reason) {
                    //failure logic
                    Log.d(CANONICAL_NAME, "fail to connect peer: " + device.deviceAddress);
                }
            });


        }
    }
}
