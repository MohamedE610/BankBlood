package com.example.bankblood.Utils.FirebaseAuthentacitionUtils;

import com.example.bankblood.Utils.Callbacks;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseHelper {

    protected Callbacks callbacks;
    protected FirebaseAuth auth;

    public FirebaseHelper(){
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

}
