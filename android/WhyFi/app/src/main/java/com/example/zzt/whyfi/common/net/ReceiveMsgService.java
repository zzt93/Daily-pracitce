package com.example.zzt.whyfi.common.net;

import android.app.Service;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

import java.io.UnsupportedEncodingException;

public class ReceiveMsgService extends Service {
    public static final int PERIOD = 30;
    private static final String CANONICAL_NAME = ReceiveMsgService.class.getCanonicalName();


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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            processIntent(intent);
        }

        Log.i(CANONICAL_NAME, "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    private void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        try {
            MsgHistory.addReceived(Message.getFromBytes(msg.getRecords()[0].getPayload()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {

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

//    private class BluetoothMsg implements Runnable {
//        @Override
//        public void run() {
//            BlueToothMsg.start();
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            BlueToothMsg.stopConnection();
//        }
//    }



}
