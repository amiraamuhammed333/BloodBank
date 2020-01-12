package com.example.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;
import com.example.bloodbank.data.model.notification.NotificationData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {


    List<NotificationData> notificationDataList = new ArrayList<>();
    Context context;


    public NotificationAdapter( List<NotificationData> notificationDataList, Context context) {
        this.notificationDataList = notificationDataList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {

        final NotificationData item = notificationDataList.get(pos);
        setData(viewHolder,pos);
        setAction(viewHolder,pos);
        //final NotificationData item = notificationDataList.get(pos);
       // viewHolder.articleAdapterTvTitle.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }


    public void setData(ViewHolder viewHolder, int pos) {
      if ( notificationDataList.get(pos).getPivot().getIsRead().equals("0")){

          viewHolder.itemNotificationIvNote.setImageResource(R.drawable.ic_notifications);
      }else{

          viewHolder.itemNotificationIvNote.setImageResource(R.drawable.ic_notifications2);
      }
      viewHolder.itemNotificationTvTitle.setText(notificationDataList.get(pos).getTitle());
      viewHolder.itemNotificationTvTime.setText(notificationDataList.get(pos).getCreatedAt());

    }

    public void setAction(ViewHolder viewHolder, int pos) {
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_notification_tv_title)
        TextView itemNotificationTvTitle;
        @BindView(R.id.item_notification_tv_time)
        TextView itemNotificationTvTime;
        @BindView(R.id.item_notification_iv_note)
        ImageView itemNotificationIvNote;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            ButterKnife.bind(this, view);

        }
    }


}
