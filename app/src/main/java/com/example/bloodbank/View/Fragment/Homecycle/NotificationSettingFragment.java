package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.adapter.NotificationSettingAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.settingNotification.NotificationSettingData;
import com.example.bloodbank.data.model.settingNotification.NotificationSettingResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationSettingFragment extends BaseFragment {
    View view;
    @BindView(R.id.notification_setting_fragment_tv)
    TextView notificationSettingFragmentTv;
    @BindView(R.id.notification_setting_fragment_blood_type)
    TextView notificationSettingFragmentBloodType;
    @BindView(R.id.notification_setting_fragment_rV_bloodType)
    RecyclerView notificationSettingFragmentRVBloodType;
    @BindView(R.id.notification_setting_fragment_government)
    TextView notificationSettingFragmentGovernment;
    @BindView(R.id.notification_setting_fragment_rv_government)
    RecyclerView notificationSettingFragmentRvGovernment;
    Unbinder unbinder;
    NotificationSettingAdapter notificationSettingAdapter;
    List<NotificationSettingData> notificationSettingDataList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    GeneralResponseData generalResponseData;
    @BindView(R.id.notification_setting_fragment_btn_save)
    Button notificationSettingFragmentBtnSave;
    private List<String> bloodTypes = new ArrayList<>(), governorates = new ArrayList<>();
    NotificationSettingAdapter governorateAdapter, bloodAdapter;

    public NotificationSettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notification_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        setUpHomeActivity();
        homeActivity.setHomeActivityTitle("Notification Setting");
        getNotificationSetting();
        initRecyclerView();
        return view;
    }

    private void setNotificationSetting() {
        APIManger.getApis().setNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", governorateAdapter.newId, bloodAdapter.newId).enqueue(new Callback<NotificationSettingResponse>() {
            @Override
            public void onResponse(Call<NotificationSettingResponse> call, Response<NotificationSettingResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationSettingResponse> call, Throwable t) {
                try {
                } catch (Exception e) {
                }
            }
        });
    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        notificationSettingFragmentRVBloodType.setLayoutManager(new GridLayoutManager(getContext(), 3));
        notificationSettingFragmentRvGovernment.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        notificationSettingAdapter= new NotificationSettingAdapter();
//        notificationSettingFragmentRVBloodType.setAdapter(notificationSettingAdapter);
//        notificationSettingFragmentRvGovernment.setAdapter(notificationSettingAdapter);
    }

    private void getNotificationSetting() {
        APIManger.getApis().getNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationSettingResponse>() {
            @Override
            public void onResponse(Call<NotificationSettingResponse> call, Response<NotificationSettingResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        bloodTypes = response.body().getData().getBloodTypes();
                        governorates = response.body().getData().getGovernorates();
                        getBloodTypes();
                        getGovernorates();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationSettingResponse> call, Throwable t) {
                try {
                } catch (Exception e) {
                }
            }
        });
    }

    private void getBloodTypes() {
        APIManger.getApis().getBloodTypes().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    bloodAdapter = new NotificationSettingAdapter(getActivity(), getActivity(), response.body().getData(), bloodTypes);
                    notificationSettingFragmentRVBloodType.setAdapter(bloodAdapter);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            }
        });
    }

    private void getGovernorates() {
        APIManger.getApis().getGovernment().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    governorateAdapter = new NotificationSettingAdapter(getActivity(), getActivity(), response.body().getData(), governorates);
                    notificationSettingFragmentRvGovernment.setAdapter(governorateAdapter);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
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

    @OnClick(R.id.notification_setting_fragment_btn_save)
    public void onClick() {
        setNotificationSetting();

    }
}
