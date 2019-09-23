package com.example.bloodbank.View.Fragment.Homecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.Adapter.ArticlesAdapter;
import com.example.bloodbank.Adapter.CustomSpinnerAdapter;
import com.example.bloodbank.Adapter.DonationAdapter;
import com.example.bloodbank.Data.api.APIManger;
import com.example.bloodbank.Data.model.Auth.ClientData;
import com.example.bloodbank.Data.model.DonationRequest.DonationItem;
import com.example.bloodbank.Data.model.DonationRequest.DonationResponse;
import com.example.bloodbank.Data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.Data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.Data.model.posts.PostData;
import com.example.bloodbank.Data.model.posts.Posts;
import com.example.bloodbank.Helper.HelperMethod;
import com.example.bloodbank.Helper.OnEndless;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.Data.local.SharedPreference.loadUserData;
import static com.example.bloodbank.Helper.HelperMethod.dismissProgressDialog;

public class DonationFragment extends BaseFragment {
    View view;
    @BindView(R.id.donation_fragment_et_government)
    EditText donationFragmentEtGovernment;
    @BindView(R.id.donation_fragment_spinner_government)
    Spinner donationFragmentSpinnerGovernment;
    @BindView(R.id.donation_fragment_et_blood_type)
    EditText donationFragmentEtBloodType;
    @BindView(R.id.donation_fragment_spinner_blood_type)
    Spinner donationFragmentSpinnerBloodType;
    Unbinder unbinder;
    List<String> bloodTypeNames = new ArrayList<> ();
    List<String> governmentNames = new ArrayList<> ();
    CustomSpinnerAdapter bloodTypeAdapter;
    CustomSpinnerAdapter governmentAdapter;
    @BindView(R.id.donation_fragment_recycler_view)
    RecyclerView donationFragmentRecyclerView;
    List<DonationItem> donationItemList = new ArrayList<> ();
    DonationAdapter donationAdapter;


    private LinearLayoutManager linearLayoutManager;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private ClientData clientData;
    private boolean Filter = false;


    public DonationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_donation, container, false );
        unbinder = ButterKnife.bind ( this, view );
        clientData = loadUserData ( getActivity () );

        getBloodTypes ();
        getGovernment ();
        initRecyclerView ();

        return view;
    }




    public void initRecyclerView() {

        linearLayoutManager = new LinearLayoutManager ( getActivity () );
        donationFragmentRecyclerView.setLayoutManager ( linearLayoutManager );

        onEndless = new OnEndless ( linearLayoutManager, 1 ) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {


                    getAllDonationRequest ( current_page );
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (Filter) {
                         //   getPostsFilter ( current_page, getId ());

                        } else {
                            getAllDonationRequest ( current_page );
                        }
                    } else {
                        dismissProgressDialog ();
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }

            }


        };
        donationFragmentRecyclerView.addOnScrollListener ( onEndless );


        donationAdapter= new DonationAdapter ( donationItemList,getActivity () );
        donationFragmentRecyclerView.setAdapter ( donationAdapter);






        getAllDonationRequest ( 1 );

        donationAdapter.setOnDetailClickListner ( new DonationAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(int position) {
                HelperMethod.ReplaceFragment ( getActivity ().getSupportFragmentManager (),
                        new DetailsFragment (), R.id.fram
                        , null, null );
            }
        } );


        donationAdapter.setOnCallClickListner ( new DonationAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(int position) {

            }
        } );


//        dataItems = new ArrayList<> ();
//        articlesAdapter = new ArticlesAdapter ( dataItems, getContext () );
//        previousPage = 1;
//        onEndless.current_page = 1;
//        onEndless.previousTotal = 0;


    }


    private void getAllDonationRequest(int page) {

       // showProgreesBar ( R.string.loading );
        APIManger.getApis ().getAllDonationRequest ( clientData.getApiToken (), page )
                .enqueue ( new Callback<DonationResponse> () {
                    @Override
                    public void onResponse(Call<DonationResponse> call, Response<DonationResponse> response) {
                        if (response.body ().getStatus () == 1) {
                            maxPage = response.body ().getData ().getLastPage ();

                            donationItemList.addAll ( response.body ().getData ().getData () );
                            donationAdapter.notifyDataSetChanged ();

                        }

                    }

                    @Override
                    public void onFailure(Call<DonationResponse> call, Throwable t) {

                        try {
                           // hideProgressDialog ();

                            Log.d ( "", "on failure : " + toString () );

                        } catch (Exception e) {

                        }

                    }
                } );

    }











    private void getGovernment() {
        APIManger.getApis ().getGovernment ()
                .enqueue ( new Callback<GeneralResponse> () {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {

                            if (response.body ().getStatus () == 1) {
                                response.body ().getData ().add ( 0, new GeneralResponseData ( getString ( R.string.government ), 0 ) );

                                governmentNames = new ArrayList<> ();
                                for (int i = 0; i < response.body ().getData ().size (); i++) {

                                    governmentNames.add ( response.body ().getData ().get ( i ).getName () );
                                }
                                governmentAdapter = new CustomSpinnerAdapter ( getActivity ()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body ().getData (), governmentNames );


                                donationFragmentSpinnerGovernment.setAdapter ( governmentAdapter );


                            } else {
                                Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();


                            }


                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        try {
                            Log.d ( "", "on failure : " + toString () );

                        } catch (Exception e) {

                        }

                    }
                } );

    }


    private void getBloodTypes() {

        APIManger.getApis ().getBloodTypes ()
                .enqueue ( new Callback<GeneralResponse> () {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {
                            if (response.body ().getStatus () == 1) {

                                response.body ().getData ().add ( 0, new GeneralResponseData ( getString ( R.string.blood_type ), 0 ) );
                                // bloodTypeIds=new ArrayList<> (  );
                                bloodTypeNames = new ArrayList<> ();

                                for (int i = 0; i < response.body ().getData ().size (); i++) {

                                    // bloodTypeIds.add ( response.body ().getData ().get ( i ).getId () );
                                    bloodTypeNames.add ( response.body ().getData ().get ( i ).getName () );

                                }
                                bloodTypeAdapter = new CustomSpinnerAdapter ( getActivity ()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body ().getData (), bloodTypeNames );


                                donationFragmentSpinnerBloodType.setAdapter ( bloodTypeAdapter );
                            } else {
                                Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();
                            }
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {

                    }
                } );


    }

    @Override
    public void onBack() {
        super.onBack ();}}