package com.example.bankblood.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankblood.R;
import com.example.bankblood.Utils.GPSUtils.GpsController;
import com.example.bankblood.Utils.GPSUtils.MyGPSTracker;


/**
 * Created by abdallah on 12/14/2017.
 */
public class MapSearchDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    Button okBtn;
    Button cancelBtn;

    public MapSearchDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_search_dialog);

        cancelBtn=(Button)findViewById(R.id.btn_cancel);
        okBtn=(Button)findViewById(R.id.btn_ok);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.btn_ok:
                okBtnWork();
                break;
            case R.id.btn_cancel:
                cancelBtnWork();
                break;
        }
    }

    private void cancelBtnWork() {
        this.cancel();
    }

    private void okBtnWork() {



        MyGPSTracker myGPSTracker=new MyGPSTracker(c);

        if(myGPSTracker.canGetLocation()) {
            double latitude, longitude;
            latitude = myGPSTracker.getLatitude();
            longitude = myGPSTracker.getLongitude();
            // Search for restaurants in San Francisco
            String uriStr = "geo:" + latitude + "," + longitude + "?q=hospitals";
            //Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=hospitals");
            Uri gmmIntentUri = Uri.parse(uriStr);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            c.startActivity(mapIntent);


        }else {
            Toast.makeText(c, "Please, turn on GPS", Toast.LENGTH_SHORT).show();
            myGPSTracker.showSettingsAlert();
        }

        cancel();
    }

    // final int facebookId=R.id.facebook_img;


}
