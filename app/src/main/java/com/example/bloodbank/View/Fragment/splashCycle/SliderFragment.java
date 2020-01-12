package com.example.bloodbank.View.Fragment.splashCycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.View.Fragment.login.LoginFragment;
import com.example.bloodbank.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SliderFragment extends BaseFragment {

    View view;
    Button btn;
    @BindView(R.id.slider_view_pager)
    ViewPager sliderViewPager;
    Unbinder unbinder;

    ViewPagerAdapter viewPagerAdapter;


    public SliderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_slider, container, false);
        unbinder = ButterKnife.bind(this, view);
        btn = view.findViewById(R.id.slider_fragment_btn);
         viewPagerAdapter = new ViewPagerAdapter(getActivity());
         sliderViewPager.setAdapter(viewPagerAdapter);
        //SharedPreference.loadBooleanData (getActivity (),"phone" );
        //  SharedPreference.loadBooleanData ( getActivity (),"password" );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.slider_fragment_btn) {

                    fragment = new LoginFragment();
                }
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fram, fragment)
                        .commit();


            }
        });

        return view;
    }







    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
