package com.osdar.androiddevprogrammingtest.features.login.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.osdar.androiddevprogrammingtest.features.login.repo.LoginRepository;
import com.osdar.androiddevprogrammingtest.features.login.repo.Result;
import com.osdar.androiddevprogrammingtest.R;
import com.osdar.androiddevprogrammingtest.features.login.util.LoginFormState;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData navigateUserToMainScreen = new MutableLiveData();
    private MutableLiveData<String> showMessage = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private FirebaseAuth mAuth;
    private MutableLiveData mReturnLoginStatus = new MutableLiveData<Result<FirebaseUser>>();


    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<String> getShowMessage() {
        return showMessage;
    }

    public LiveData getNavigation() {
        return navigateUserToMainScreen;
    }

    public LiveData<Result<FirebaseUser>> getReturnLoginStatus() {
        return mReturnLoginStatus;
    }

    public void login(String email, String password) {
        loginRepository.login(email, password, mReturnLoginStatus);
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public void checkLoginStatus() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateUserToMainScreen.postValue(null);
        }
    }


}
