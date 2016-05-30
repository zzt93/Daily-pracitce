package com.example.zzt.whyfi.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.databinding.ActivityOthersInfoBinding;
import com.example.zzt.whyfi.model.Device;

public class OthersInfoActivity extends AppCompatActivity {

    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            device = Device.getFromIntent(intent);
        } else {
            device = Device.getFromBundle(savedInstanceState);
        }
        ActivityOthersInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_others_info);
        binding.setOtherDevice(device);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        device.addToBundle(outState);
        super.onSaveInstanceState(outState);
    }
}
