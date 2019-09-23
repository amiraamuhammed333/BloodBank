package com.example.bloodbank.View.Activity;

import android.support.v7.app.AppCompatActivity;

import com.example.bloodbank.View.Fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed ();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
