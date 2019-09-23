package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bloodbank.Adapter.TabAdapter;
import com.example.bloodbank.Helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_fragment_tablayout)
    TabLayout homeFragmentTablayout;
    @BindView(R.id.home_fragment_view_pager)
    ViewPager homeFragmentViewPager;
    Unbinder unbinder;

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_home, container, false );
        unbinder = ButterKnife.bind ( this, view );

        homeFragmentTablayout.setTabGravity ( TabLayout.GRAVITY_FILL );

        final TabAdapter adapter = new TabAdapter ( getFragmentManager () );
        adapter.addPage ( new ArticlesFragment (), getString ( R.string.articles ) );
        adapter.addPage ( new DonationFragment (), getString ( R.string.donation ) );

        homeFragmentViewPager.setAdapter ( adapter );
        homeFragmentTablayout.setupWithViewPager ( homeFragmentViewPager );

        return view;
    }

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
