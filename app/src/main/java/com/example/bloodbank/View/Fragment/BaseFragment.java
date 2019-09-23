package com.example.bloodbank.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;

public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void setUpActivity(){

        baseActivity = (BaseActivity) getActivity ();
        baseActivity.baseFragment = this;

    }

    public void onBack() {
        baseActivity.superBackPressed ();
    }
}
