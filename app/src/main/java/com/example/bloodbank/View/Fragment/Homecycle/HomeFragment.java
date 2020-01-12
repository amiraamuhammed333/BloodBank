package com.example.bloodbank.View.Fragment.Homecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.adapter.TabAdapter;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_fragment_tablayout)
    TabLayout homeFragmentTablayout;
    @BindView(R.id.home_fragment_view_pager)
    ViewPager homeFragmentViewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;

    View view;
    private DonationFragment donationFragment;

    public HomeFragment() { }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpHomeActivity();
        setUpActivity ();
        view = inflater.inflate ( R.layout.fragment_home, container, false );
        unbinder = ButterKnife.bind ( this, view );
        homeActivity.setHomeActivityTitle ( " " );
        homeFragmentTablayout.setTabGravity ( TabLayout.GRAVITY_FILL );
       // homeFragmentTablayout.setSelectedTabIndicatorColor ( R.color.colorPrimaryDark );
        donationFragment = new DonationFragment ();
        final TabAdapter adapter = new TabAdapter ( getChildFragmentManager () );
        adapter.addPage ( new ArticlesFragment (), getString ( R.string.articles ) );
        adapter.addPage ( donationFragment, getString ( R.string.donation ) );
        homeFragmentViewPager.setAdapter ( adapter );
        homeFragmentTablayout.setupWithViewPager ( homeFragmentViewPager );
        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Request_Donation_Fragment request_donation_fragment = new Request_Donation_Fragment ();
                request_donation_fragment.donationFragment = donationFragment;
                HelperMethod.ReplaceFragment ( getActivity ().getSupportFragmentManager (),
                        request_donation_fragment, R.id.fram
                        , null, null ); }} );
        return view; }

    @Override
    public void onBack() {
        getActivity ().finish ();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }

}
