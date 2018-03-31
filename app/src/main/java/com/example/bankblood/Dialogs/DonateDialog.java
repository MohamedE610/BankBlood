package com.example.bankblood.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankblood.Models.Donner.Donner;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.RestApiRequests.AddDonationRequest;
import com.example.bankblood.Utils.RestApiRequests.GetDonnerByIDRequest;


/**
 * Created by abdallah on 12/14/2017.
 */
public class DonateDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    Button okBtn, cancelBtn;

    EditText userName,phone,bloodType,area,city;

    String userNameStr,phoneStr,bloodTypeStr,areaStr,cityStr;
    private Integer id;

    public DonateDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.donate_dialog);


        MySharedPreferences.setUpMySharedPreferences(c);
        id=Integer.valueOf(MySharedPreferences.getUserSetting("id"));

        okBtn=(Button)findViewById(R.id.btn_ok);
        cancelBtn=(Button)findViewById(R.id.btn_cancel);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        userName=(EditText) findViewById(R.id.user_name);
        phone=(EditText) findViewById(R.id.phone);
        bloodType=(EditText) findViewById(R.id.blood_type);
        area=(EditText) findViewById(R.id.area);
        city=(EditText) findViewById(R.id.city);

        userName.setEnabled(false);
        phone.setEnabled(false);
        bloodType.setEnabled(false);
        area.setEnabled(false);
        city.setEnabled(false);

        displayDonnerData();
    }

    private void displayDonnerData() {
        GetDonnerByIDRequest getDonnerByIDRequest=new GetDonnerByIDRequest(id);
        getDonnerByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Donner donner=(Donner)obj;
                userName.setText(donner.data.name);
                phone.setText(donner.data.phone);
                bloodType.setText(donner.data.bloodType);
                area.setText(donner.data.region);
                city.setText(donner.data.city);
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(c, "لقد حدث خطاء الرجاء اعادة المحاولة ", Toast.LENGTH_SHORT).show();
                cancel();
            }

        });
        getDonnerByIDRequest.start();
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
        AddDonationRequest addDonationRequest=new AddDonationRequest(id);
        addDonationRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(c, "العملية تمت بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(c, "لقد حدث خطاء الرجاء اعادة المحاولة ", Toast.LENGTH_SHORT).show();
            }
        });

        addDonationRequest.start();
    }

}
