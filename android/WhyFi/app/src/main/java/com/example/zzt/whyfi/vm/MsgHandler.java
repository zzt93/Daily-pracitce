package com.example.zzt.whyfi.vm;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.example.zzt.whyfi.R;

/**
 * Created by zzt on 5/26/16.
 * <p/>
 * Usage:
 */
public class MsgHandler {
    public void onClickSend(View view) {
        EditText text = (EditText) view.findViewById(R.id.msg_to_send);
        Editable msg = text.getText();
        // sent by network
        // add to sent storage and update list view
    }

}
