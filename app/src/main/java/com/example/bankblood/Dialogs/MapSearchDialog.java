package com.example.bankblood.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.bankblood.R;


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

    }

    // final int facebookId=R.id.facebook_img;


}
