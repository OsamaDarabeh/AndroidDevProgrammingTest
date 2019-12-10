package com.osdar.androiddevprogrammingtest.features.home.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.osdar.androiddevprogrammingtest.R;
import com.osdar.androiddevprogrammingtest.databinding.ActivityHomeBinding;
import com.osdar.androiddevprogrammingtest.features.home.models.Item;
import com.osdar.androiddevprogrammingtest.features.home.viewModel.HomeViewModel;
import com.osdar.androiddevprogrammingtest.features.login.view.LoginActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private ExpandableListView recyclerView;

    public static Intent newInstance(Activity context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);
        initView();
        viewModel.init();
        observation();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            viewModel.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
        //respond to menu item selection

    }

    /**
     * observations
     */
    private void observation() {
        viewModel.mListOfStoreLiveData.observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                viewModel.checkTheReturnedData(items);
            }
        });
        viewModel.showTheDataToUI.observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                prepareListData(items);
            }
        });

        viewModel.navigateUserToLoginScreen.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                navigateToLoginScreen();
            }
        });
    }

    private void navigateToLoginScreen() {
        startActivity(LoginActivity.newInstance(this));
        finish();
    }

    /**
     * init the view
     */
    private void initView() {
        recyclerView = findViewById(R.id.home_expandableListView);
        setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));
    }


    /**
     * @param items the list of items came from Firebase database.
     */
    private void prepareListData(ArrayList<Item> items) {
        ItemExpandableAdapter adapter = new ItemExpandableAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
