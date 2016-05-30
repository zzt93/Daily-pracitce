package com.example.zzt.whyfi.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.databinding.ActivityEditDesBinding;
import com.example.zzt.whyfi.model.Device;

public class EditDesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditDesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_des);
        Intent intent = getIntent();
        binding.setDes(intent.getStringExtra(Device.intentDes));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveEditDes(View view) {
        EditText textView = (EditText) findViewById(R.id.editDesText);
        assert textView != null;
        String s = textView.getText().toString();
        Device.now.setDes(s);
        // jump back
        finish();
    }
}
