package com.example.bloodbank.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bloodbank.helper.HelperMethod;
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
