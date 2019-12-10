package com.osdar.androiddevprogrammingtest.features.login.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    private FirebaseUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public void login(String email, String password, MutableLiveData mListOfStoreLiveData) {
        // handle login
        dataSource.login(email, password, mListOfStoreLiveData);

    }
}
