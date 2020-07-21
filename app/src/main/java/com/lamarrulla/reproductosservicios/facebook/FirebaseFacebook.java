package com.lamarrulla.reproductosservicios.facebook;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lamarrulla.reproductosservicios.utils.UtilsActivity;

public class FirebaseFacebook {
    public final String TAG = this.getClass().getSimpleName();
    static Boolean isLogOk = false;
    UtilsActivity utilsActivity;

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setToken(AccessToken token) {
        this.token = token;
    }

    private FirebaseAuth mAuth;
    private Context context;
    private AccessToken token;

    private void savePicturProfile(){
        /* make the API call */
        new GraphRequest(
                token.getCurrentAccessToken(),
                "/v6.0/me/picture?redirect=0&height=200&width=200&type=normal",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        Log.i(TAG, response.getJSONArray().toString());
                    }
                }
        ).executeAsync();
    }

    public Boolean handleFacebookAccessToken() {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            savePicturProfile();

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            utilsActivity = new UtilsActivity();
                            utilsActivity.setContext(context);
                            utilsActivity.CallPSActivity();

                            //updateUI(user);
                            isLogOk = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(context, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            isLogOk = false;
                        }
                    }
                });
        return isLogOk;
    }

}
