package com.example.zzt.whyfi.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.databinding.ActivityDeviceInfoBinding;
import com.example.zzt.whyfi.model.Device;

public class DeviceInfoActivity extends AppCompatActivity {

    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device = Device.now;
        ActivityDeviceInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_device_info);
        binding.setThisDevice(device);
    }

    public void editName(View view) {
        Intent intent = new Intent(this, EditNameActivity.class);
        intent.putExtra(Device.intentName, device.getName());
        startActivity(intent);
    }

    public void editDes(View view) {
        Intent intent = new Intent(this, EditDesActivity.class);
        intent.putExtra(Device.intentDes, device.getDes());
        startActivity(intent);
    }

    public void chooseAvatar(View view) {
        // TODO: 5/30/16 choose avatar
//        Intent intent = new Intent(this, );
//        intent.putExtra(Device.intentAvatar, device.getAvatar());
    }
}
