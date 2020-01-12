package com.example.bloodbank.View.Fragment.Homecycle;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.DonationRequest.DonationItem;
import com.example.bloodbank.data.model.DonationRequest.OneDonationResponse;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.HomeActivity;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class DetailsFragment extends BaseFragment implements LocationListener, OnMapReadyCallback {

    @BindView(R.id.detail_fragment_donation_request)
    TextView detailFragmentDonationRequest;
    @BindView(R.id.detail_fragment_name)
    TextView detailFragmentName;
    @BindView(R.id.detail_fragment_age)
    TextView detailFragmentAge;
    @BindView(R.id._detail_fragment_blood_type)
    TextView DetailFragmentBloodType;
    @BindView(R.id.detail_fragment_number_of_bag_required)
    TextView detailFragmentNumberOfBagRequired;
    @BindView(R.id.detail_fragment_hospital)
    TextView detailFragmentHospital;
    @BindView(R.id.detail_fragment_hospital_address)
    TextView detailFragmentHospitalAddress;
    @BindView(R.id.detail_fragment_phone)
    TextView detailFragmentPhone;
    @BindView(R.id.detail_fragment_details)
    TextView detailFragmentDetails;
    @BindView(R.id.detail_fragment_map_view)
    MapView detailFragmentMapView;
    @BindView(R.id.detail_fragment_call)
    Button detailFragmentCall;
    Unbinder unbinder;

    private ClientData clientData;
    public DonationItem donationRequest;
    public int id;
    private String longitude, latitude;
    View view;
    GoogleMap googleMap;
    Marker marker = null;
    HomeActivity homeActivity;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        detailFragmentMapView.onCreate(savedInstanceState);
        setUpActivity();
        setUpHomeActivity();
        detailFragmentMapView.getMapAsync(this);
        clientData = loadUserData(getActivity());
        getDonationDatails();
        return view;
    }

    private void getDonationDatails() {
        APIManger.getApis().getDonationDetails(clientData.getApiToken(), id)
                .enqueue(new Callback<OneDonationResponse>() {
                    @Override
                    public void onResponse(Call<OneDonationResponse> call, Response<OneDonationResponse> response) {
                        try {

                            if (response.body().getStatus() == 1) {

                                donationRequest = (response.body().getData());
                                latitude = donationRequest.getLatitude();
                                longitude = donationRequest.getLongitude();

                                getDonation();
                            }


                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<OneDonationResponse> call, Throwable t) {
                        try {


                            // hideProgressDialog ();

                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) { } }});
    }

    private void getDonation() {

        detailFragmentDonationRequest.setText(getString(R.string.donation_request) + " " + donationRequest.getPatientName());
        detailFragmentName.setText(getString(R.string.name) + " " + donationRequest.getPatientName());
        detailFragmentAge.setText(getString(R.string.age) + " " + donationRequest.getPatientAge());
        DetailFragmentBloodType.setText(getString(R.string.blood_type) + " " + donationRequest.getBloodType().getName());
        detailFragmentNumberOfBagRequired.setText(getString(R.string.number_of_bags_required) + " " + donationRequest.getBagsNum());
        detailFragmentHospital.setText(getString(R.string.hospital) + " " + donationRequest.getHospitalName());
        detailFragmentHospitalAddress.setText(getString(R.string.hospital_address) + " " + donationRequest.getHospitalAddress());
        detailFragmentPhone.setText(getString(R.string.phone) + " " + donationRequest.getPhone());
        detailFragmentDetails.setText(getString(R.string.details) + " " + donationRequest.getNotes());
        detailFragmentMapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latLng = new LatLng
                        (Double.valueOf(donationRequest.getLongitude()), Double.valueOf(donationRequest.getLatitude()));
                marker = googleMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Location"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
            }
        });


        detailFragmentCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + donationRequest.getPhone()));
                startActivity(intent);
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
        detailFragmentMapView.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

    }

    @Override
    public void onStart() {
        super.onStart();
        detailFragmentMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        detailFragmentMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        detailFragmentMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        detailFragmentMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        detailFragmentMapView.onLowMemory();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
