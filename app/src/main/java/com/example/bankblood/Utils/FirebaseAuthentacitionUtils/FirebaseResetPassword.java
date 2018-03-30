package com.example.bankblood.Utils.FirebaseAuthentacitionUtils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseResetPassword extends FirebaseHelper {

    public FirebaseResetPassword(){

    }


    public void resetPasswordWithFirebase(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callbacks.OnSuccess(task);
                        } else {
                            callbacks.OnFailure(task);
                        }
                    }
                });
    }
}
