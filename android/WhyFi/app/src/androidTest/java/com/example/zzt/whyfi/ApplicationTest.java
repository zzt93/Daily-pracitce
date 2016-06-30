package com.example.zzt.whyfi;

import android.test.ApplicationTestCase;
import android.util.Log;

import com.example.zzt.whyfi.common.App;
import com.example.zzt.whyfi.common.DeviceUuidFactory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<App> {

    public ApplicationTest(Class<App> applicationClass) {
        super(applicationClass);
    }

    public ApplicationTest() {
        super(App.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    public void testMyAppUUID() throws Exception {
        for (int i = 0; i < 10; i++) {
            DeviceUuidFactory factory = new DeviceUuidFactory(getContext());
            Log.i(this.getName(), factory.getDeviceUuid().toString());
        }
    }
}