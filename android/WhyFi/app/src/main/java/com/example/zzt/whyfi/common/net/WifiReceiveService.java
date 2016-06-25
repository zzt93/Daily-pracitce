package com.example.zzt.whyfi.common.net;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WifiReceiveService extends Service {
    private static final String CANONICAL_NAME = ReceiveMsgService.class.getCanonicalName();

    public WifiReceiveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(CANONICAL_NAME, "in on create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(CANONICAL_NAME, "in on start");
        return super.onStartCommand(intent, flags, startId);

    }
}
