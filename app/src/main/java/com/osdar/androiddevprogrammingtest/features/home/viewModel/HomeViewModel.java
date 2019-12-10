package com.osdar.androiddevprogrammingtest.features.home.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.osdar.androiddevprogrammingtest.features.home.models.Item;
import com.osdar.androiddevprogrammingtest.features.home.repo.HomeRepository;

import java.util.ArrayList;


public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    private HomeRepository homeRepository = new HomeRepository();
    public MutableLiveData mListOfStoreLiveData = new MutableLiveData<ArrayList<Item>>();
    public MutableLiveData showTheDataToUI = new MutableLiveData<ArrayList<Item>>();
    public MutableLiveData navigateUserToLoginScreen = new MutableLiveData();

    public ObservableField<Boolean> showNoItemsFoundMessage = new ObservableField<>(false);
    public ObservableField<Boolean> showTheProgressBar = new ObservableField<>(false);

    public void init() {
        showTheProgressBar.set(true);
        homeRepository.getData(mListOfStoreLiveData);
    }

    public void onDestroy() {
        homeRepository.removeListener();
    }

    public void checkTheReturnedData(ArrayList<Item> items) {
        showTheProgressBar.set(false);

        if (items.isEmpty()) {
            showNoItemsFoundMessage.set(true);
        } else {
            showNoItemsFoundMessage.set(false);
            showTheDataToUI.postValue(items);
        }
    }

    public void logout() {
        FirebaseAuth instance = FirebaseAuth.getInstance();
        instance.signOut();
        navigateUserToLoginScreen.postValue(null);
    }
}
