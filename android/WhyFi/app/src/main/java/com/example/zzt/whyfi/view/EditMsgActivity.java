package com.example.zzt.whyfi.view;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;

public class EditMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_msg);
    }

    public void onClickSend(View view) {
        EditText text = (EditText) findViewById(R.id.msg_to_send);
        assert text != null;
        String msg = text.getText().toString();
        // sent by network
        // add to sent storage and update list view
        MsgHistory.addSent(new Message(Device.now, msg));
        // jump back
        NavUtils.navigateUpFromSameTask(this);
    }
}
