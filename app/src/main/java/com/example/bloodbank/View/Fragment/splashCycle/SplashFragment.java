package com.example.bloodbank.View.Fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.View.Activity.HomeActivity;
import com.example.bloodbank.View.Fragment.Homecycle.HomeFragment;
import com.example.bloodbank.data.local.SharedPreference;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import static com.example.bloodbank.data.local.SharedPreference.REMEMBER;
import static com.example.bloodbank.data.local.SharedPreference.USER_DATA;
import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class SplashFragment extends BaseFragment {

    View view;
    ClientData clientData;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_splash, container, false );
        clientData = loadUserData ( getActivity () );


        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                if (SharedPreference.loadBooleanData ( getActivity (), REMEMBER ) &&
                        SharedPreference.loadUserData ( getActivity () ) != null) {
                    Intent intent = new Intent ( getActivity (), HomeActivity.class


                    );
                    startActivity ( intent );


                } else {
                    HelperMethod.ReplaceFragment ( getActivity ().getSupportFragmentManager (),
                            new SliderFragment (), R.id.fram
                            , null, null );
                }

            }
        }, 3000 );

        return view;
    }
}
