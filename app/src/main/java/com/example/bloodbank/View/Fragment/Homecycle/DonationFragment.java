package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.adapter.CustomSpinnerAdapter;
import com.example.bloodbank.adapter.DonationAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.DonationRequest.DonationItem;
import com.example.bloodbank.data.model.DonationRequest.DonationResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.helper.OnEndless;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

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
    List<String> bloodTypeNames = new ArrayList<>();
    List<String> governmentNames = new ArrayList<>();
    CustomSpinnerAdapter bloodTypeAdapter;
    CustomSpinnerAdapter governmentAdapter;
    @BindView(R.id.donation_fragment_recycler_view)
    RecyclerView donationFragmentRecyclerView;
    public List<DonationItem> donationItemList = new ArrayList<>();
    public DonationAdapter donationAdapter;
    @BindView(R.id.donation_fragment_ib_filter)
    ImageButton donationFragmentIbFilter;


    private LinearLayoutManager linearLayoutManager;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private ClientData clientData;
    private boolean filter = false;


    public DonationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_donation, container, false);
        unbinder = ButterKnife.bind(this, view);
        clientData = loadUserData(getActivity());
        getBloodTypes();
        getGovernment();
        initRecyclerView();
        return view;
    }


    public void initRecyclerView() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        donationFragmentRecyclerView.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {


                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndless.privious_page = current_page;
                        if (filter) {
                            onFilter(current_page);

                        } else {
                            getAllDonationRequest(current_page);
                        }
                    } else {
                        onEndless.current_page = onEndless.privious_page;
                    }
                } else {
                    onEndless.current_page = onEndless.privious_page;
                }
            }
        };
        donationFragmentRecyclerView.addOnScrollListener(onEndless);
        donationAdapter = new DonationAdapter(getActivity(), getActivity(),donationItemList);
        donationFragmentRecyclerView.setAdapter(donationAdapter);
        getAllDonationRequest(1);
//        donationAdapter.setOnDetailClickListner(new DonationAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//                DetailsFragment detailsFragment = new DetailsFragment();
//                detailsFragment.id = donationItemList.get(position).getId();
//                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), detailsFragment
//                        , R.id.fram
//                        , null, null);
//            }
//        });
//
//        donationAdapter.setOnCallClickListner(new DonationAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//        });
    }


    private void getAllDonationRequest(int page) {

        APIManger.getApis().getAllDonationRequest(clientData.getApiToken(), page)
                .enqueue(new Callback<DonationResponse>() {
                    @Override
                    public void onResponse(Call<DonationResponse> call, Response<DonationResponse> response) {
                        if (response.body().getStatus() == 1) {
                            maxPage = response.body().getData().getLastPage();

                            donationItemList.addAll(response.body().getData().getData());
                            donationAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<DonationResponse> call, Throwable t) {

                        try {

                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {
                        }
                    }
                });

    }


    private void getGovernment() {
        APIManger.getApis().getGovernment()
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {

                            if (response.body().getStatus() == 1) {
                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.government), 0));

                                governmentNames = new ArrayList<>();
                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    governmentNames.add(response.body().getData().get(i).getName());
                                }
                                governmentAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), governmentNames);


                                donationFragmentSpinnerGovernment.setAdapter(governmentAdapter);


                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();


                            }


                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        try {
                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {

                        }

                    }
                });

    }


    private void getBloodTypes() {

        APIManger.getApis().getBloodTypes()
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {
                            if (response.body().getStatus() == 1) {

                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.blood_type), 0));
                                // bloodTypeIds=new ArrayList<> (  );
                                bloodTypeNames = new ArrayList<>();

                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    // bloodTypeIds.add ( response.body ().getData ().get ( i ).getId () );
                                    bloodTypeNames.add(response.body().getData().get(i).getName());

                                }
                                bloodTypeAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), bloodTypeNames);


                                donationFragmentSpinnerBloodType.setAdapter(bloodTypeAdapter);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
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

    @OnClick(R.id.donation_fragment_ib_filter)
    public void onViewClicked() {
        onFilter(1);
    }

    private void onFilter(int page) {

        filter = true;
        onEndless.previousTotal = 0;
        onEndless.current_page = 1;
        onEndless.privious_page = 1;
        donationItemList = new ArrayList<>();
        donationAdapter = new DonationAdapter(getActivity(),getActivity(),donationItemList);
        donationFragmentRecyclerView.setAdapter(donationAdapter);


        APIManger.getApis().getFilter(clientData.getApiToken(), page, bloodTypeAdapter.selectId, governmentAdapter.selectId)
                .enqueue(new Callback<DonationResponse>() {
                    @Override
                    public void onResponse(Call<DonationResponse> call, Response<DonationResponse> response) {
                        try {

                            if (response.body().getStatus() == 1) {
                                maxPage = response.body().getData().getLastPage();

                                donationItemList.addAll(response.body().getData().getData());
                                donationAdapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<DonationResponse> call, Throwable t) {

                        try {

                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {
                        }
                    }
                });

    }
}