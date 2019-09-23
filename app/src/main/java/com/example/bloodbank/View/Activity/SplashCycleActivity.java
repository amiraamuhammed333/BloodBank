package com.example.bloodbank.View.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bloodbank.Helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.splashCycle.SplashFragment;

public class SplashCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_spalsh_cycle );

        HelperMethod.ReplaceFragment ( getSupportFragmentManager (), new SplashFragment (), R.id.fram
                , null, null );
    }
}
