package com.example.bankblood.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.FirebaseAuthentacitionUtils.FirebaseSignUp;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    String name,phone,email,gender,bloodTypeStr,areaStr,cityStr;;

    Spinner spinnerBloodType,spinnerArea,spinnerCity;

    RadioButton maleRadioBtn,femaleRadioBtn;

    private EditText inputEmail, inputPassword,inputUserName,inputPhoneNum,inputPasswordConfirm;

    private Button btnSignIn, btnSignUp, btnResetPassword;

    private ProgressBar progressBar;

    //private FirebaseAuth auth;

    FirebaseSignUp firebaseSignUp;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        firebaseSignUp=new FirebaseSignUp();



        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);


        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputUserName = (EditText) findViewById(R.id.user_name);
        inputPhoneNum = (EditText) findViewById(R.id.phone_num);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPasswordConfirm = (EditText) findViewById(R.id.password_confirm);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        maleRadioBtn = (RadioButton) findViewById(R.id.male_rb);
        femaleRadioBtn = (RadioButton) findViewById(R.id.female_rb);

        maleRadioBtn.setOnCheckedChangeListener(this);
        femaleRadioBtn.setOnCheckedChangeListener(this);

        spinnerBloodType=(Spinner)findViewById(R.id.spinner_blood_type);
        spinnerArea=(Spinner)findViewById(R.id.spinner_area);
        spinnerCity=(Spinner)findViewById(R.id.spinner_city);

        createBloodTypeSpinner();
        createCitySpinner();
        createِAreaSpinner();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = inputUserName.getText().toString().trim();
                phone = inputPhoneNum.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String passwordConfirm = inputPasswordConfirm.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "ادخل البريد الالكترونى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "ادخل كلمه السر", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "ادخل اسم المستخدم", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(),"تاكيد كلمه السر غير صحيح", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.password_too_short_enter_minimum_6_characters, Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseSignUp.setCallbacks(new Callbacks() {

                    @Override
                    public void OnSuccess(Object obj) {

                        Task<AuthResult> task = (Task<AuthResult>) obj;
                        Toast.makeText(SignupActivity.this, getString(R.string.createUserwithemailoncomplete) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        try {

                        }catch (Exception e){}

                        /*startActivity(new Intent(SignupActivity.this, HomeActivity.class));

                        finish();*/
                    }

                    @Override
                    public void OnFailure(Object obj) {
                        Task<AuthResult> task = (Task<AuthResult>) obj;
                        Toast.makeText(SignupActivity.this, getString(R.string.createUserwithemailoncomplete) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignupActivity.this, getString(R.string.authentication_failed) + task.getException(),
                                Toast.LENGTH_SHORT).show();
                    }

                });

                firebaseSignUp.signUpWithFirebase(email,password);

                progressBar.setVisibility(View.VISIBLE);
            }

        });

    }



    @Override

    protected void onResume() {

        super.onResume();

        progressBar.setVisibility(View.GONE);

    }





    String[] spinnerBloodTypeData= {"A","A+","A-","B","B+","B-","O+","O-","AB","AB+","AB-"};
    String[] bloodTypeValues={"A","A+","A-","B","B+","B-","O+","O-","AB","AB+","AB-"};
    private void createBloodTypeSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,spinnerBloodTypeData);
        spinnerBloodType.setAdapter(SpinnerAdapter);
        spinnerBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bloodTypeStr=bloodTypeValues[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    String[] spinnerAreaData={"الثالثة","الثانية","الاولى"};
    String[] areaValues={"الرياض","جده","الدوادمى"};
    private void createِAreaSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,spinnerAreaData);
        spinnerArea.setAdapter(SpinnerAdapter);
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areaStr=areaValues[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    String[] spinnerCityData={"الرياض","جده","الدوادمى"};
    String[] cityValues={"الرياض","جده","الدوادمى"};
    private void createCitySpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,spinnerCityData);
        spinnerCity.setAdapter(SpinnerAdapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cityStr=cityValues[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();

        switch (id) {
            case R.id.male_rb:
                if (b)
                    gender = "male";
                break;

            case R.id.female_rb:
                if (b)
                    gender = "female";
                break;
        }

    }


}