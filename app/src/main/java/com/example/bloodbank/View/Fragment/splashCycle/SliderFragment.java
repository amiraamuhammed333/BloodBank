package com.example.bloodbank.View.Fragment.splashCycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.View.Fragment.login.LoginFragment;


public class SliderFragment extends BaseFragment  {

    View view;
    Button btn;


    public SliderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }

    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_slider, container, false );
        btn=view.findViewById ( R.id.slider_fragment_btn );
        btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(view.getId ()==R.id.slider_fragment_btn)
                {
                    fragment = new LoginFragment ();
                }
               getActivity ()
                       .getSupportFragmentManager ()
                       .beginTransaction ()
                       .replace (R.id.fram,fragment  )
                       .commit ();


            }
        } );
        return view;
    }



}
