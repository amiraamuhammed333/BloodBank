package com.example.bloodbank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<> ();
    List<String> fragmentsTitle = new ArrayList<> ();

    public TabAdapter(FragmentManager fm) {
        super ( fm );
    }

    public void addPage(Fragment fragment, String title) {
        fragments.add ( fragment );
        fragmentsTitle.add ( title );
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {

        return fragments.get ( position );

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get ( position );
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return fragments.size ();
    }
}
