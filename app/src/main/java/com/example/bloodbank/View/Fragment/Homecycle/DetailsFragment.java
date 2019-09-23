package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Adapter.DonationDetailAdapter;
import com.example.bloodbank.Data.model.DonationRequest.DonationItem;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsFragment extends BaseFragment {
    View view;
    @BindView(R.id.details_fragment_recycler_view_)
    RecyclerView detailsFragmentRecyclerView;
    Unbinder unbinder;
    DonationDetailAdapter donationDetailAdapter;
    private LinearLayoutManager linearLayoutManager;
    List<DonationItem> donationItemList = new ArrayList<> ();


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_details, container, false );
        unbinder = ButterKnife.bind ( this, view );
        initRecyclerView ();

        return view;
    }

    private void initRecyclerView() {

        linearLayoutManager = new LinearLayoutManager ( getActivity () );
        detailsFragmentRecyclerView.setLayoutManager ( linearLayoutManager );
        donationDetailAdapter=new DonationDetailAdapter ( donationItemList,getContext () );
        detailsFragmentRecyclerView.setAdapter ( donationDetailAdapter );

    }

    @Override
    public void onBack() {
        super.onBack ();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }
}
