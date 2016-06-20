package com.example.zzt.whyfi.common.net.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.widget.Toast;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.common.App;

import java.net.InetAddress;
import java.util.Collection;

/**
 * Created by zzt on 6/16/16.
 * <p/>
 * Usage:
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
    // TODO: 6/20/16 test and make it faster
    // TODO: 6/20/16 show app name : plain string?
    // TODO: 6/20/16 life cycle of this receiver -- app?

    public static final String CANONICAL_NAME = WiFiDirectBroadcastReceiver.class.getCanonicalName();
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Activity mActivity;
    /**
     * this task will run as long as this activity alive
     */
    private ServerAsyncTask serverAsyncTask;
    public final static IntentFilter mIntentFilter = new IntentFilter();

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel
            , Activity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    static {
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    private volatile ConnectionState state = ConnectionState.NONE;

    public void setState(ConnectionState state) {
        Log.d(CANONICAL_NAME, "" + this.state + "->" + state);
        this.state = state;
    }

    @UiThread
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(CANONICAL_NAME, "action received: " + action);

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
            } else {
                // Wi-Fi P2P is not enabled
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, mActivity.getString(R.string.WIFI_not_enable), Toast.LENGTH_SHORT).show();
                        mActivity.finish();
                    }
                });
            }

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
            if (state != ConnectionState.NONE) {
                tryConnect();
                return;
            }
            setState(ConnectionState.FOUND_PEER);
            WifiP2pDeviceList list = intent.getParcelableExtra(WifiP2pManager.EXTRA_P2P_DEVICE_LIST);
            handleDeviceList(list);
//            if (mManager != null) {
//                WifiP2pManager.PeerListListener myPeerListListener = new ConnectAllPeer(mManager, mChannel);
//                mManager.requestPeers(mChannel, myPeerListListener);
//                mManager.stopPeerDiscovery(mChannel, null);
//            }

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections

            NetworkInfo networkInfo = intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            WifiP2pInfo info = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);
            Log.d(CANONICAL_NAME, "connected: " + networkInfo.isConnected() + ";" + info.toString());
            if (networkInfo.isConnected()) {
                setState(ConnectionState.GROUP_CREATED);
                // We are connected with the other device, request connection
                // info to find group owner IP
//                mManager.requestConnectionInfo(mChannel, this);
                onConnectionInfoAvailable(info);
            }

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
            Log.d(CANONICAL_NAME, intent.toString());
        }
    }

    private void handleDeviceList(WifiP2pDeviceList list) {
        App application = (App) mActivity.getApplication();
        if (application.getList().size() == list.getDeviceList().size()) {
            return;
        }
        application.setList(list);
        tryConnect();
    }

    private boolean tryConnect() {
        App application = (App) mActivity.getApplication();
        Collection<WifiP2pDevice> deviceList = application.getList();

        for (final WifiP2pDevice device : deviceList) {
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = device.deviceAddress;
            setState(ConnectionState.CONNECTING);
            mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    Log.d(CANONICAL_NAME, "succeed to connect peer: " + device.deviceAddress);
                    setState(ConnectionState.CONNECT_SUCC);
                }

                @Override
                public void onFailure(int reason) {
                    setState(ConnectionState.NONE);
                    Log.d(CANONICAL_NAME, "fail to connect peer: " + device.deviceAddress);
                }
            });
        }
        return !deviceList.isEmpty();
    }

    public void discover() {
        startServer();

        if (!tryConnect()) {
            /**
             * The discovery remains active until a connection is initiated or a p2p group is formed
             */
            mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    Log.d(CANONICAL_NAME, "succeed to find peer");
                }

                @Override
                public void onFailure(int reasonCode) {
                    Log.d(CANONICAL_NAME, "fail to find peer");
                }
            });
        }
    }

    private volatile boolean serverStarted = false;

    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        // InetAddress from WifiP2pInfo struct.
        InetAddress groupOwnerAddress = info.groupOwnerAddress;

        // After the group negotiation, we can determine the group owner.
        if (info.groupFormed && info.isGroupOwner) {
            Log.d(CANONICAL_NAME, "group owner: " + groupOwnerAddress.getHostAddress());

            // Do whatever tasks are specific to the group owner.
            // One common case is creating a server thread and accepting
            // incoming connections.
            startServer();
        } else if (info.groupFormed) {
            Log.d(CANONICAL_NAME, "group client: " + groupOwnerAddress.getHostAddress());

            // The other device acts as the client. In this case,
            // you'll want to create a client thread that connects to the group
            // owner.
            new ClientAsyncTask(this, groupOwnerAddress.getHostAddress()).execute((Void[]) null);
        } else {
            Log.e(CANONICAL_NAME, "group not formed");
        }
    }

    private void startServer() {
        if (!serverStarted) {
            serverAsyncTask = new ServerAsyncTask(this);
            serverAsyncTask.execute((Void[]) null);
            serverStarted = true;
        }
    }

    /**
     * invoked when destroy, so no need to reset state, serverStar
     */
    @UiThread
    public void cleanUp() {
        if (serverStarted) {
            serverAsyncTask.stopListen();
        }
    }

    @WorkerThread
    public void resetServerState() {
        setState(ConnectionState.NONE);
        serverStarted = false;
    }
}


