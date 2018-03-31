package com.example.bankblood.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.Constants;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.RestApiRequests.NewMessageRequest;

import java.util.HashMap;


/**
 * Created by abdallah on 12/14/2017.
 */
public class SendMessageDialog extends Dialog implements View.OnClickListener {

    public Context c;
    public Dialog d;

    int to_id;
    Button okBtn;
    Button cancelBtn;

    EditText msgTitle,msgBody;
    String title,body;
    private Integer id;

    public SendMessageDialog(Context a , int to_id) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.to_id=to_id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_message_dialog);


        MySharedPreferences.setUpMySharedPreferences(c);
        id=Integer.valueOf(MySharedPreferences.getUserSetting("id"));
        msgTitle=(EditText)findViewById(R.id.message_title);
        msgBody=(EditText)findViewById(R.id.message_body);

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
       title=msgTitle.getText().toString();
       body=msgBody.getText().toString();

       if(TextUtils.isEmpty(title)){
           Toast.makeText(c, "ادخل عنوان الرسالة", Toast.LENGTH_SHORT).show();
           return;
       }

        if(TextUtils.isEmpty(body)){
            Toast.makeText(c, "ادخل بعض الكلمات رجاء", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("title",title);
        hashMap.put("body",body);
        hashMap.put("to",to_id+"");
        hashMap.put("from",id+"");

        NewMessageRequest newMessageRequest=new NewMessageRequest(hashMap);
        newMessageRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(c, "تم ارسال الرسالة بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(c, "لقد حدث خطاء اثناء ارسال الرسالة الرجاء اعد المحاولة", Toast.LENGTH_SHORT).show();
            }
        });
        newMessageRequest.start();
    }


}
