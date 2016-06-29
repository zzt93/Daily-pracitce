package com.example.zzt.whyfi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.zzt.whyfi.view.LoginActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


public class TestLogin extends ActivityInstrumentationTestCase2<LoginActivity> {
  	private Solo solo;
  	
  	public TestLogin() {
		super(LoginActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.example.zzt.whyfi.view.LoginActivity'
		solo.waitForActivity(com.example.zzt.whyfi.view.LoginActivity.class, 2000);
        //Set default small timeout to 70093 milliseconds
		Timeout.setSmallTimeout(70093);
        //Click on ImageView
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.start_go_btn));
        //Wait for activity: 'com.example.zzt.whyfi.view.Drawer'
		assertTrue("com.example.zzt.whyfi.view.Drawer is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.Drawer.class));
        //Click on 设置 FrameLayout
		solo.clickInRecyclerView(9, 0);
        //Wait for activity: 'com.example.zzt.whyfi.view.DeviceSettingsActivity'
		assertTrue("com.example.zzt.whyfi.view.DeviceSettingsActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.DeviceSettingsActivity.class));
        //Click on Data & sync
		solo.clickInList(3, 0);
        //Wait for activity: 'com.example.zzt.whyfi.view.DeviceSettingsActivity'
		assertTrue("com.example.zzt.whyfi.view.DeviceSettingsActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.DeviceSettingsActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.example.zzt.whyfi.view.DeviceSettingsActivity'
		assertTrue("com.example.zzt.whyfi.view.DeviceSettingsActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.DeviceSettingsActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.example.zzt.whyfi.view.Drawer'
		assertTrue("com.example.zzt.whyfi.view.Drawer is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.Drawer.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on this phone: GT-I9507V a boy want to make success by his hand
		solo.clickOnText(java.util.regex.Pattern.quote("this phone: GT-I9507V"));
        //Wait for activity: 'com.example.zzt.whyfi.view.DeviceInfoActivity'
		assertTrue("com.example.zzt.whyfi.view.DeviceInfoActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.DeviceInfoActivity.class));
        //Click on this phone: GT-I9507V
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.textView2));
        //Wait for activity: 'com.example.zzt.whyfi.view.EditNameActivity'
		assertTrue("com.example.zzt.whyfi.view.EditNameActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.EditNameActivity.class));
        //Click on this phone: GT-I9507V
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.editNameText));
        //Enter the text: 'this phone: GT-I9507V   '
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.zzt.whyfi.R.id.editNameText));
		solo.enterText((android.widget.EditText) solo.getView(com.example.zzt.whyfi.R.id.editNameText), "this phone: GT-I9507V。。。");
        //Click on 保存
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.button2));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.example.zzt.whyfi.view.Drawer'
		assertTrue("com.example.zzt.whyfi.view.Drawer is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.Drawer.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.fab));
        //Wait for activity: 'com.example.zzt.whyfi.view.EditMsgActivity'
		assertTrue("com.example.zzt.whyfi.view.EditMsgActivity is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.EditMsgActivity.class));
        //Enter the text: '回复到家了'
		solo.clearEditText((android.widget.EditText) solo.getView(com.example.zzt.whyfi.R.id.msg_to_send));
		solo.enterText((android.widget.EditText) solo.getView(com.example.zzt.whyfi.R.id.msg_to_send), "回复到家了");
        //Click on 发送
		solo.clickOnView(solo.getView(com.example.zzt.whyfi.R.id.msg_send));
        //Wait for activity: 'com.example.zzt.whyfi.view.Drawer'
		assertTrue("com.example.zzt.whyfi.view.Drawer is not found!", solo.waitForActivity(com.example.zzt.whyfi.view.Drawer.class));
	}
}
