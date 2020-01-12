package com.example.bloodbank.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodbank.data.model.DonationRequest.DonationItem;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;
import com.example.bloodbank.View.Fragment.Homecycle.DetailsFragment;
import com.example.bloodbank.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    FragmentActivity baseActivity;
    List<DonationItem> donationItemList = new ArrayList<>();
    Context context;

    public DonationAdapter(Context context, FragmentActivity activity, List<DonationItem> donationItemList) {
        this.donationItemList = donationItemList;
        this.context = context;
        baseActivity = (BaseActivity) activity;


        //this.baseActivty = (BaseActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_donate_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {

        final DonationItem item = donationItemList.get(pos);

        viewHolder.donateItemTvUser.setText(context.getString(R.string.name) + "        " + item.getPatientName());
        viewHolder.donateItemTvHospital.setText(context.getString(R.string.hospital) + "        " + item.getHospitalName());
        viewHolder.donateItemTvCity.setText(context.getString(R.string.city) + "        " + item.getCity().getName());
        viewHolder.donateItemTvBloodType.setText(item.getBloodType().getName());
        viewHolder.donateItemBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + item.getPhone()));
                context.startActivity(intent);
            }
        });

//        if (onCallClickListner != null) {
//            viewHolder.donateItemBtnCall.setOnClickListener ( new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//
//                    intent.setData( Uri.parse("tel:" + item.getPhone ()));
//                    context.startActivity(intent);
//
//                }
//            } );
//        }
        viewHolder.donateItemBtnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.id = donationItemList.get(pos).getId();
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(),
                        detailsFragment, R.id.fram
                        , null, null);
            }
        });


    }

    @Override
    public int getItemCount() {
        return donationItemList.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.donate_item_tv_user)
        TextView donateItemTvUser;
        @BindView(R.id.donate_item_tv_hospital)
        TextView donateItemTvHospital;
        @BindView(R.id.donate_item_tv_city)
        TextView donateItemTvCity;
        @BindView(R.id.donate_item_tv_blood_type)
        TextView donateItemTvBloodType;
        @BindView(R.id.donate_item_btn_details)
        Button donateItemBtnDetails;
        @BindView(R.id.donate_item_btn_call)
        Button donateItemBtnCall;
        View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}

