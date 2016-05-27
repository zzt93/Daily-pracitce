package com.example.zzt.whyfi.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.databinding.ActivityDeviceInfoBinding;
import com.example.zzt.whyfi.model.Device;

public class DeviceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeviceInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_device_info);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(getString(R.string.device_name));
        binding.setOtherDevice(new Device(stringExtra));
    }
}
