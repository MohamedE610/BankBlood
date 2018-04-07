package com.example.bankblood.Services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.bankblood.Activities.InboxActivity;
import com.example.bankblood.R;
import com.example.bankblood.Utils.MySharedPreferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            try {
                Map map=remoteMessage.getData();
                String msg=map.get("message").toString();
                String sender=map.get("sender").toString();
                //Set set=map.keySet();
                //set.toArray();
                JSONObject json = new JSONObject(sender);
                MySharedPreferences.setUpMySharedPreferences(getApplicationContext());

                int id=Integer.valueOf(MySharedPreferences.getUserSetting("id"));
                //int reciever_id

                handleDataMessage(json, msg);
            } catch (Exception e) {}
        }


    }

    private void handleNotification(String message) {

    }

    private void handleDataMessage(JSONObject json, String msg) {
        try {
            Notification notification=createNotification(json.getString("name"),msg);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(435,notification);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, InboxActivity.class);
        return PendingIntent.getActivity(this, 678, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification(String sender ,String msg) {

        PendingIntent pendingIntent=createPendingIntent();

        Notification notification=new Notification.Builder(this)
                .setContentTitle("بنك الدم")
                .setSubText(sender)
                .setContentText(msg)
                .setSmallIcon(R.drawable.circlar_logo)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }

}
