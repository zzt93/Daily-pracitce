package com.example.zzt.whyfi.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.UiThread;
import android.text.TextUtils;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.view.DeviceSettingsActivity;
import com.example.zzt.whyfi.view.Drawer;

/**
 * Created by zzt on 6/12/16.
 * <p/>
 * Usage:
 */
@UiThread
public class NotificationHelper {

    private static Context context = App.getContext();
    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private static int NOTIFICATION = R.string.local_service_started;
    private NotificationManager mNM;


    public NotificationHelper() {
        mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Show a notification while this service is running.
     *
     * @param msg_received_label string resource id
     */
    public void showFixedNotification(int msg_received_label) {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = context.getText(R.string.app_name);
        showNotification(text, text, context.getText(msg_received_label));
    }

    public void showNotification(CharSequence ticker, CharSequence title, CharSequence text) {

        SharedPreferences notificationPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean notify = notificationPref.getBoolean(DeviceSettingsActivity.NOTIFICATIONS_NEW_MESSAGE, true);
        if (!notify) {
            return;
        }
        String ring = notificationPref.getString(DeviceSettingsActivity.NOTIFICATIONS_NEW_MESSAGE_RINGTONE, "");
        if (TextUtils.isEmpty(ring)) {
            return;
        }
        Uri ringUri = Uri.parse(ring);

        boolean vibrate = notificationPref.getBoolean(DeviceSettingsActivity.NOTIFICATIONS_NEW_MESSAGE_VIBRATE, true);
        if (!vibrate) {
            return;
        }

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, Drawer.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker(ticker)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(title)  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setSound(ringUri)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .build();

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);


    }


    public void cancel() {
        mNM.cancel(NOTIFICATION);
    }
}
