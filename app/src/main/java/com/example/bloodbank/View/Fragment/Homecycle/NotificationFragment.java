package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.adapter.NotificationAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.notification.NotificationData;
import com.example.bloodbank.data.model.notification.NotificationResponse;
import com.example.bloodbank.helper.OnEndless;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class NotificationFragment extends BaseFragment {
    View view;
    @BindView(R.id.view_notification_rV)
    RecyclerView recyclerViewNotificationSetting;
    LinearLayoutManager linearLayoutManager;
    NotificationAdapter notificationAdapter;
    List<NotificationData> notificationDataList = new ArrayList<>();
    ClientData clientData;
    private OnEndless onEndless;
    private int previousPage = 1;
    private int maxPage = 0;
    Unbinder unbinder;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_notification
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        setUpHomeActivity();
        homeActivity.setHomeActivityTitle("Notification");
        clientData = loadUserData(getActivity());

        init();

        return view;
    }

    private void init() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewNotificationSetting.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        getNotification(current_page);

                    } else {
                        onEndless.current_page = previousPage;
                    }
                }
            }


        };
        recyclerViewNotificationSetting.addOnScrollListener(onEndless);

        notificationAdapter = new NotificationAdapter(notificationDataList, getContext());
        recyclerViewNotificationSetting.setAdapter(notificationAdapter);
        getNotification(1);
    }

    private void getNotification(int page) {
        APIManger.getApis().getNotification("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page)
                .enqueue(new Callback<NotificationResponse>() {
                    @Override
                    public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {

                        try {

                            if (response.body().getStatus() == 1) {
                                maxPage = response.body().getData().getLastPage();
                                notificationDataList.addAll(response.body().getData().getData());
                                notificationAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationResponse> call, Throwable t) {

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
