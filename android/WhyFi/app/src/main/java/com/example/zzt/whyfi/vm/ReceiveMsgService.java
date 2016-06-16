package com.example.zzt.whyfi.vm;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.common.net.BlueToothMsg;
import com.example.zzt.whyfi.common.NotificationHelper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReceiveMsgService extends Service {
    public static final int PERIOD = 30;
    private static final String CANONICAL_NAME = ReceiveMsgService.class.getCanonicalName();
    private ScheduledExecutorService scheduler;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.

    public ReceiveMsgService() {
    }

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        ReceiveMsgService getService() {
            return ReceiveMsgService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.i(CANONICAL_NAME, "in on create");

        NotificationHelper.init(this);
//        BlueToothMsg.init(ReceiveMsgService.this);

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(new MsgJob(), 0, PERIOD, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(CANONICAL_NAME, "Received start id " + startId + ": " + intent);
        if (scheduler == null) {
            Log.e(CANONICAL_NAME, "impossible");
        }
        return START_NOT_STICKY;
    }

    private class MsgJob implements Runnable {
        @Override
        public void run() {
            BlueToothMsg.start();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BlueToothMsg.stopConnection();
        }
    }

    @Override
    public void onDestroy() {
        scheduler.shutdown();

        // Tell the user we stopped.
        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();



}
