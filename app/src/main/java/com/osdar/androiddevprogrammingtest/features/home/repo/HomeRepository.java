package com.osdar.androiddevprogrammingtest.features.home.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.osdar.androiddevprogrammingtest.features.home.models.Item;

import java.util.ArrayList;

public class HomeRepository {


    private static final String TAG = HomeRepository.class.getSimpleName();
    private static final String ITEMS_KEY_NAME = "items";
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(ITEMS_KEY_NAME);
    private MutableLiveData mListOfStoreLiveData;

    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.e(TAG, dataSnapshot.toString());
            ArrayList<Item> list = new ArrayList<>();

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Item item = snapshot.getValue(Item.class);
                list.add(item);
            }

            mListOfStoreLiveData.postValue(list);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Failed to read value.", error.toException());
        }
    };

    /**
     * return the list of data.
     *
     * @param mListOfStoreLiveData
     */
    public void getData(MutableLiveData<ArrayList<Item>> mListOfStoreLiveData) {
        this.mListOfStoreLiveData = mListOfStoreLiveData;
        myRef.addValueEventListener(listener);
    }

    /**
     * remove the listener.
     */
    public void removeListener() {
        myRef.removeEventListener(listener);
    }

}
