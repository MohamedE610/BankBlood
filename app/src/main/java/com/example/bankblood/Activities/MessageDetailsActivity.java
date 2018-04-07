package com.example.bankblood.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bankblood.Models.Messages.MessageData;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.NetworkState;
import com.example.bankblood.Utils.RestApiRequests.MarkMessageAsReadRequest;

public class MessageDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    MarkMessageAsReadRequest markMessageAsReadRequest;
    String name, title, body;
    TextView nameView, titleView, bodyView;
    int person_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        setTitle("الرسالة");

        nameView=(TextView)findViewById(R.id.person_name);
        titleView=(TextView)findViewById(R.id.msg_title);
        bodyView=(TextView)findViewById(R.id.msg_body);


        Intent intent=getIntent();
        String action=intent.getAction();
        MessageData messageData=(MessageData) intent.getSerializableExtra("message");


        if(messageData!=null) {
            if (action != null && action.equals("to")) {
                name = messageData.to.name;
                person_id=messageData.to.id;
            } else if (action != null && action.equals("from")) {
                name = messageData.from.name;
                person_id=messageData.from.id;
            }

            title=messageData.title;
            body=messageData.body;

            markMessageAsReadMethod(messageData.id);

        }

        nameView.setText(name);
        titleView.setText(title);
        bodyView.setText(body);

        nameView.setOnClickListener(this);
    }

    private void markMessageAsReadMethod(int id) {
        if(NetworkState.ConnectionAvailable(this)){
            markMessageAsReadRequest=new MarkMessageAsReadRequest(id);
            markMessageAsReadRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                }

                @Override
                public void OnFailure(Object obj) {

                }
            });
            markMessageAsReadRequest.start();
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.person_name:
                openProfileActivity();
                break;
        }
    }

    private void openProfileActivity() {
        Intent intent=new Intent(this,ProfileActivity.class);
        intent.putExtra("person_id",person_id);
        startActivity(intent);
    }
}
