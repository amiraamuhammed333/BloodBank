package com.example.bloodbank.View.Fragment.Homecycle;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.adapter.CustomSpinnerAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.DonationRequest.DonationResponse;
import com.example.bloodbank.data.model.DonationRequest.OneDonationResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.Map.MapActivity;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.helper.HelperMethod;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
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
import static com.example.bloodbank.View.Activity.Map.MapActivity.latitude;
import static com.example.bloodbank.View.Activity.Map.MapActivity.longitude;

public class Request_Donation_Fragment extends BaseFragment {
    @BindView(R.id.fragment_request_donation_name)
    EditText fragmentRequestDonationName;
    @BindView(R.id.fragment_request_donation_age)
    EditText fragmentRequestDonationAge;
    @BindView(R.id.fragment_request_donation_blood_type)
    EditText fragmentRequestDonationBloodType;
    @BindView(R.id.fragment_request_donation_spinner_blood_type)
    Spinner fragmentRequestDonationSpinnerBloodType;
    @BindView(R.id.fragment_request_donation_number_of_bags)
    EditText fragmentRequestDonationNumberOfBags;
    @BindView(R.id.fragment_request_donation_hospital_name)
    EditText fragmentRequestDonationHospitalName;
    @BindView(R.id.fragment_request_donation_hospital_address)
    EditText fragmentRequestDonationHospitalAddress;
    @BindView(R.id.fragment_request_donation_)
    EditText fragmentRequestDonation;
    @BindView(R.id.fragment_request_donation_location)
    ImageView fragmentRequestDonationLocation;
    @BindView(R.id.fragment_request_donation_government)
    EditText fragmentRequestDonationGovernment;
    @BindView(R.id.fragment_request_donation_spinner_government)
    Spinner fragmentRequestDonationSpinnerGovernment;
    @BindView(R.id.fragment_request_donation_city)
    EditText fragmentRequestDonationCity;
    @BindView(R.id.fragment_request_donation_spinner_city)
    Spinner fragmentRequestDonationSpinnerCity;
    @BindView(R.id.fragment_request_donation_phone)
    EditText fragmentRequestDonationPhone;
    @BindView(R.id.fragment_request_donation_notes)
    EditText fragmentRequestDonationNotes;
    @BindView(R.id.fragment_request_donation_sending_request)
    Button fragmentRequestDonationSendingRequest;
    Unbinder unbinder;

    ClientData clientData;
    MapActivity mapActivity;
    GoogleMap googleMap;
    Fragment fragment;
    public DonationFragment donationFragment;
    View view;

    List<String> bloodTypeNames = new ArrayList<> ();
    List<String> governmentNames = new ArrayList<> ();
    List<String> citiesNames = new ArrayList<> ();

    CustomSpinnerAdapter bloodTypeAdapter;
    CustomSpinnerAdapter governmentAdapter;
    CustomSpinnerAdapter cityAdapter;


    public Request_Donation_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setUpActivity ();
        setUpHomeActivity ();
       view = inflater.inflate ( R.layout.fragment_request_donation, container, false );
        unbinder = ButterKnife.bind ( this, view );
        clientData = loadUserData ( getActivity () );
        getBloodTypes ();
        getgovernment ();
        getBloodBags ();
        homeActivity.setHomeActivityTitle ( "Request Donation" );
        return view;

    }

    private void getBloodBags(){


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


                                fragmentRequestDonationSpinnerBloodType.setAdapter ( bloodTypeAdapter );
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

    private void getgovernment() {

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


                                fragmentRequestDonationSpinnerGovernment.setAdapter ( governmentAdapter );
                                fragmentRequestDonationSpinnerGovernment.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                        if (position != 0) {
                                            getCities ( governmentAdapter.selectId );
                                        } else {
                                            fragmentRequestDonationCity.setVisibility ( View.GONE );
                                            fragmentRequestDonationSpinnerCity.setVisibility ( View.GONE );

                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {


                                    }
                                } );

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

    private void getCities(int selectId) {
        APIManger.getApis ().getCities ( selectId )
                .enqueue ( new Callback<GeneralResponse> () {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {
                            if (response.body ().getStatus () == 1) {

                                fragmentRequestDonationCity.setVisibility ( View.VISIBLE );
                                fragmentRequestDonationSpinnerCity.setVisibility ( View.VISIBLE );

                                response.body ().getData ().add ( 0, new GeneralResponseData ( getString ( R.string.city ), 0 ) );
                                // bloodTypeIds=new ArrayList<> (  );
                                citiesNames = new ArrayList<> ();

                                for (int i = 0; i < response.body ().getData ().size (); i++) {

                                    // bloodTypeIds.add ( response.body ().getData ().get ( i ).getId () );
                                    citiesNames.add ( response.body ().getData ().get ( i ).getName () );

                                }
                                cityAdapter = new CustomSpinnerAdapter ( getActivity ()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body ().getData (), citiesNames );


                                fragmentRequestDonationSpinnerCity.setAdapter ( cityAdapter );
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


    public void mapSearch(View view) {
        String hospital_address = fragmentRequestDonationHospitalAddress.getText ().toString ();
        List<Address> addressList = null;

        if (hospital_address != null || !hospital_address.equals ( "" )) {
            Geocoder geocoder = new Geocoder ( getActivity () );
            try {
                addressList = geocoder.getFromLocationName ( hospital_address, 1 );

            } catch (IOException e) {
                e.printStackTrace ();
            }
            Address address = addressList.get ( 0 );
            LatLng latLng = new LatLng ( address.getLatitude (), address.getLongitude () );
            googleMap.addMarker ( new MarkerOptions ().position ( latLng ).title ( "Marker" ) );
            googleMap.animateCamera ( CameraUpdateFactory.newLatLng ( latLng ) );
        }


    }


    public void sendingRequest() {
        String name = fragmentRequestDonationName.getText().toString();
        String age = fragmentRequestDonationAge.getText ().toString ();
        int blood_type_id = fragmentRequestDonationSpinnerBloodType.getSelectedItemPosition();
        String number_of_blood_bage = fragmentRequestDonationNumberOfBags.getText().toString();
        String hospital_name = fragmentRequestDonationHospitalName.getText().toString ();
        String hospital_address = fragmentRequestDonationAge.getText().toString();
        int city_id = fragmentRequestDonationSpinnerGovernment.getSelectedItemPosition();
        String phone = fragmentRequestDonationPhone.getText().toString();
        String notes = fragmentRequestDonationNotes.getText().toString();

        APIManger.getApis ().createRequest ( clientData.getApiToken (), name, age, blood_type_id, number_of_blood_bage,
                hospital_name, hospital_address, city_id, phone, notes, longitude, latitude )
                .enqueue ( new Callback<DonationResponse> () {
                    @Override
                    public void onResponse(Call<DonationResponse> call, Response<DonationResponse> response) {

                        try {
                            if (response.body ().getStatus () == 1) {

                              // donationFragment.donationItemList.add ( 0, response.body ().getData() );
                             //   donationFragment.donationAdapter.notifyDataSetChanged ();


                            }
                            Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();


                        } catch (Exception e) {

                        }


                    }

                    @Override
                    public void onFailure(Call<DonationResponse> call, Throwable t) {

                    }
                } );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }

    @OnClick({R.id.fragment_request_donation_location, R.id.fragment_request_donation_sending_request})
    public void onViewClicked(View view) {
        switch (view.getId ()) {
            case R.id.fragment_request_donation_location:
                Intent intent = new Intent ( getActivity (), MapActivity.class );
                startActivity ( intent );
                break;
            case R.id.fragment_request_donation_sending_request:
                sendingRequest ();
                fragment = new DonationFragment();


                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fram, fragment)
                        .commit();
                break;
            case R.id.fragment_request_donation_hospital_address:
                mapSearch ( view );
                break;
        }
    }

    @OnClick(R.id.fragment_request_donation_hospital_address)
    public void onViewClicked() {
    }
}
