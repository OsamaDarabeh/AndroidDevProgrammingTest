package com.osdar.androiddevprogrammingtest.features.login.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private static final String TAG = LoginDataSource.class.getSimpleName();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private MutableLiveData mListOfStoreLiveData;

    public void login(final String email, final String password, final MutableLiveData mListOfStoreLiveData) {
        this.mListOfStoreLiveData = mListOfStoreLiveData;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mListOfStoreLiveData.postValue(new Result.Success<>(user));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            mListOfStoreLiveData.postValue(new Result.Error(new IOException("Error logging in", task.getException())));
                            register(email, password, mListOfStoreLiveData);
                        }

                    }
                });
    }

    private void register(String email, String password, final MutableLiveData mListOfStoreLiveData) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mListOfStoreLiveData.postValue(new Result.Success<>(user));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            mListOfStoreLiveData.postValue(new Result.Error(new IOException("Error logging in", task.getException())));
                        }

                    }
                });
    }

    public void logout() {
        mAuth.signOut();
    }
}
