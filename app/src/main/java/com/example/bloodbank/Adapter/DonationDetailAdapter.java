package com.example.bloodbank.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloodbank.Data.model.DonationRequest.DonationItem;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationDetailAdapter extends RecyclerView.Adapter<DonationDetailAdapter.ViewHolder> {


    private BaseActivity baseActivty;
    List<DonationItem> donationItemList = new ArrayList<> ();
    Context context;

    public DonationDetailAdapter(List<DonationItem> donationItemList, Context context) {
        this.donationItemList = donationItemList;
        this.context = context;

        //this.baseActivty = (BaseActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () )
                .inflate ( R.layout.item_detail_donation, viewGroup, false );
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {

        final DonationItem item = donationItemList.get ( pos );

        viewHolder.itemDetailDonationDonationRequest.setText ( context.getString ( R.string.donation_request ) + " " + item.getPatientName () );
        viewHolder.itemDetailDonationName.setText ( context.getString ( R.string.name ) + " " + item.getPatientName () );
        viewHolder.itemDetailDonationAge.setText ( context.getString ( R.string.age ) + " " + item.getPatientAge () );
        viewHolder.itemDetailDonationBloodType.setText ( context.getString ( R.string.blood_type) + " " + item.getBloodType ()  );
        viewHolder.itemDetailDonationNumberOfBagRequired.setText ( context.getString ( R.string.number_of_bags_required) + " " + item.getBagsNum ()  );
        viewHolder.itemDetailDonationHospital.setText ( context.getString ( R.string.hospital) + " " + item.getHospitalName () );
        viewHolder.itemDetailDonationHospitalAddress.setText ( context.getString ( R.string.hospital_address) + " " + item.getHospitalAddress ()  );
        viewHolder.itemDetailDonationPhone.setText ( context.getString ( R.string.phone) + " " + item.getPhone ()  );
        viewHolder.itemDetailDonationDetails.setText ( context.getString ( R.string.details) + " " + item.getNotes ()  );
        viewHolder.itemDetailDonationMapView.getMapAsync ( new OnMapReadyCallback () {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        } );





    }

    @Override
    public int getItemCount() {
        return donationItemList.size ();
    }







    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_detail_donation_donation_request)
        TextView itemDetailDonationDonationRequest;
        @BindView(R.id.item_detail_donation_name)
        TextView itemDetailDonationName;
        @BindView(R.id.item_detail_donation_age)
        TextView itemDetailDonationAge;
        @BindView(R.id.item_detail_donation_blood_type)
        TextView itemDetailDonationBloodType;
        @BindView(R.id.item_detail_donation_number_of_bag_required)
        TextView itemDetailDonationNumberOfBagRequired;
        @BindView(R.id.item_detail_donation_hospital)
        TextView itemDetailDonationHospital;
        @BindView(R.id.item_detail_donation_hospital_address)
        TextView itemDetailDonationHospitalAddress;
        @BindView(R.id.item_detail_donation_phone)
        TextView itemDetailDonationPhone;
        @BindView(R.id.item_detail_donation_details)
        TextView itemDetailDonationDetails;
        @BindView(R.id.item_detail_donation_map_view)
        MapView itemDetailDonationMapView;
        View view;

        public ViewHolder(View itemView) {
            super ( itemView );
            view = itemView;
            ButterKnife.bind ( this, view );
        }
    }
}

