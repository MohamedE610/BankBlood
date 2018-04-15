package com.example.bankblood.Activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.RestApiRequests.GetIncomingMessagesRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MySharedPreferences.setUpMySharedPreferences(this);

        setTitle("بنك الدم");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Create an Intent that will start the Menu-Activity.
                /*MySharedPreferences.setUpMySharedPreferences(SplashActivity.this);
                MySharedPreferences.setUserSetting("id","3");*/
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }


}
