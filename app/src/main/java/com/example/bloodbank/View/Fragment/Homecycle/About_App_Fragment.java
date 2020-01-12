package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.Client;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.aboutApp.AboutResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class About_App_Fragment extends BaseFragment {
    View view;
    @BindView(R.id.about_app_tv1)
    TextView aboutAppTv1;
    Unbinder unbinder;
    ClientData clientData;

    public About_App_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        setUpHomeActivity ();
        homeActivity.setHomeActivityTitle("about app");
        clientData = loadUserData(getActivity());
        setUpActivity();
        getInformation();
        return view;
    }

    private void getInformation() {
        APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
            @Override
            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                aboutAppTv1.setText(response.body().getData().getAboutApp());
            }

            @Override
            public void onFailure(Call<AboutResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
