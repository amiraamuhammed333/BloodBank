package com.example.bloodbank.View.Fragment.splashCycle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

public class SplashFragment extends BaseFragment {

    View view;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_splash, container, false );

        new Handler ().postDelayed( new Runnable(){
            @Override
            public void run() {
                HelperMethod.ReplaceFragment ( getActivity ().getSupportFragmentManager (),


                        new SliderFragment (), R.id.fram
                        , null, null );

            }
        }, 3000);

        return view;
    }
}
