package com.example.rent.accoutputreadingappwithfragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AccOutputFragmentHolder {

    private FragmentManager fragmentManager;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        listFragment = new ListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.activity_main,listFragment);
        transaction.commit();



    }

    @Override
    public void switchToDetailFragment(String filePath) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setFilePath(filePath);
        transaction.replace(R.id.activity_main,detailFragment);
        transaction.commit();

    }

    @Override
    public void switchToListFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main,listFragment);
        transaction.commit();

    }


}
