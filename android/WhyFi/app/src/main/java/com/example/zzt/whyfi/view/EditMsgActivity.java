package com.example.zzt.whyfi.view;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.common.net.wifi.WiFiDirectBroadcastReceiver;
import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

public class EditMsgActivity extends AppCompatActivity {
    private WiFiDirectBroadcastReceiver mReceiver;
    private MsgHistory msgHistory = new MsgHistory();

    //    NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_msg);

//        if (!WifiMsg.setUpWifi(this)) {
//            Toast.makeText(this, R.string.net_not_enabled, Toast.LENGTH_SHORT).show();
//        }
    }

    public void onClickSend(View view) {
        EditText text = (EditText) findViewById(R.id.msg_to_send);
        assert text != null;
        String msg = text.getText().toString();
        if (msg.isEmpty()) {
            text.setError(getString(R.string.empty_msg));
            return;
        }
        // sent by network
        Message message = new Message(Device.now, msg);
        // add to sent storage and update list view
        msgHistory.writeMsg(message);
        // jump back
        NavUtils.navigateUpFromSameTask(this);
    }


    public void setReceiver(WiFiDirectBroadcastReceiver receiver) {
        mReceiver = receiver;
    }

    public WiFiDirectBroadcastReceiver getReceiver() {
        return mReceiver;
    }

}
