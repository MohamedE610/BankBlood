package com.example.bankblood.utils;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseHelper {

    protected Callback callback;
    protected FirebaseAuth auth;

    public FirebaseHelper(){
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}
