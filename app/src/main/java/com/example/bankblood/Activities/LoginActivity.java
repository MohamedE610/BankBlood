package com.example.bankblood.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.FirebaseAuthentacitionUtils.FirebaseSignIn;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.RestApiRequests.GetDonorByFirebaseIDRequest;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    //private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset,btnEixt;
    FirebaseSignIn firebaseSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("تسجيل الدخول");
        //Get Firebase auth instance
        //auth = FirebaseAuth.getInstance();
        MySharedPreferences.setUpMySharedPreferences(this);
        firebaseSignIn=new FirebaseSignIn();

        //if (auth.getCurrentUser() != null) {
        if(firebaseSignIn.getFirebaseUser()!=null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnEixt = (Button) findViewById(R.id.btn_exit);

        //Get Firebase auth instance
        //auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_email_address, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseSignIn.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Task<AuthResult> task = (Task<AuthResult>) obj;
                        String firebase_id=task.getResult().getUser().getUid();

                        GetDonorByFirebaseIDRequest getDonorByFirebaseIDRequest =new GetDonorByFirebaseIDRequest(firebase_id);
                        getDonorByFirebaseIDRequest.setCallbacks(new Callbacks() {
                            @Override
                            public void OnSuccess(Object obj) {

                                Donor donor =(Donor)obj;
                                MySharedPreferences.setUserSetting("id", donor.data.id+"");

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "welcome ^ ^ ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void OnFailure(Object obj) {
                                Toast.makeText(LoginActivity.this, "please, try again", Toast.LENGTH_SHORT).show();
                            }
                        });
                        getDonorByFirebaseIDRequest.start();
                    }

                    @Override
                    public void OnFailure(Object obj) {

                        if (password.length() < 6) {
                            inputPassword.setError(getString(R.string.minimum_password));
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                firebaseSignIn.signInWithFirebase(email,password);
            }
        });


        btnEixt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

