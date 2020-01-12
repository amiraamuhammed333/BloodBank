package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.settingNotification.NotificationSettingData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

NotificationSettingAdapter extends RecyclerView.Adapter<NotificationSettingAdapter.ViewHolder> {


    private Context context;
    private BaseActivity baseActivity;
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private List<String> oldId = new ArrayList<>();
    public List<Integer> newId = new ArrayList<>();


    public NotificationSettingAdapter(Context context, FragmentActivity activity, List<GeneralResponseData> generalResponseDataList, List<String> oldId) {
        this.context = context;
        baseActivity = (BaseActivity) context;
        this.generalResponseDataList = generalResponseDataList;
        this.oldId = oldId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification_checkbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final GeneralResponseData item = generalResponseDataList.get(position);
        viewHolder.itemNotificationCb.setText(item.getName());
        if (oldId.contains(String.valueOf(item.getId()))) {
            viewHolder.itemNotificationCb.setChecked(true);
            newId.add(item.getId());
        } else {
            viewHolder.itemNotificationCb.setChecked(false);
        }
        viewHolder.itemNotificationCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewHolder.itemNotificationCb.isChecked()) {
                    for (int i = 0; i < newId.size(); i++) {
                        if (newId.get(i).equals(item.getId())) {
                            newId.remove(i);
                            break;
                        }
                    }
                } else {
                    newId.add(item.getId());
                }
            }
        });
    }


    private void setAction(ViewHolder viewHolder, int position) {

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return generalResponseDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notification_cb)
        CheckBox itemNotificationCb;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
