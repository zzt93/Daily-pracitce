package com.example.zzt.whyfi;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.example.zzt.whyfi.common.DeviceUuidFactory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        for (int i = 0; i < 10; i++) {
            DeviceUuidFactory factory = new DeviceUuidFactory(getContext());
            Log.i(this.getName(), factory.getDeviceUuid().toString());
        }
    }
}