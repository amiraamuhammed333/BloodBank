package com.example.bloodbank.View.Activity.Map;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_Access_GPS = 500;

    @BindView(R.id.map_mapview)
    com.google.android.gms.maps.MapView mapMapview;
    @BindView(R.id.map_tv_location)
    TextView mapTvLocation;
    GoogleMap googleMap;

    Marker marker=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_map );
        ButterKnife.bind ( this );


        mapMapview.onCreate ( savedInstanceState );
        mapMapview.getMapAsync ( this );


        if (isGPSPermissionAllowed ()) {

            //call your  function
            initializeGPS ();
        } else {
            //request permisssion
            requestLocationPermision ();
        }

    }

    public void setMyLocation(){
        if (location!=null&&googleMap!=null){
            LatLng latLng = new LatLng
                    ( location.getLatitude (),location.getLongitude () );
            if(marker==null) {
                marker = googleMap.addMarker ( new MarkerOptions ().position ( latLng )
                        .title ( "My Location" )
                );
            } else marker.setPosition(latLng);



            googleMap.animateCamera ( CameraUpdateFactory.newLatLngZoom ( latLng,12.0f ));

        }}

    public boolean isGPSPermissionAllowed(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;

    }


    MyLocationProvider locationProvider;
    Location location;

    public void initializeGPS(){
        Toast.makeText ( this,"GPS ALLOWED",Toast.LENGTH_SHORT ).show ();
        locationProvider = new MyLocationProvider ( this );
        location = locationProvider.getBestLastKnownLocation ();

        if (location==null){
            Toast.makeText ( this,"cannot get your location",Toast.LENGTH_SHORT ).show ();
        }
        else {
            mapTvLocation.setText ( location.getLatitude ()+""+location.getLongitude () );
            Log.e ( "location",location.toString () );
        }
        locationProvider.trackLocation ( this );
    }


    private void requestLocationPermision() {


        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            //showDialog

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle ( R.string.warning )
                    .setTitle ( R.string.message_request_GPS_reason )
                    .setPositiveButton ( R.string.ok, new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss ();
                            ActivityCompat.requestPermissions(MapActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_Access_GPS);
                        }
                    } ).show ();

        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_Access_GPS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Access_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    initializeGPS ();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText ( this, "Permiossioin Denied App Canaot Access GPS ", Toast.LENGTH_SHORT ).show ();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap=googleMap;
        setMyLocation ();


    }




    @Override
    protected void onStart() {
        super.onStart ();
        mapMapview.onStart ();
    }

    @Override
    protected void onResume() {
        super.onResume ();
        mapMapview.onResume ();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        mapMapview.onPause ();
    }

    @Override
    protected void onStop() {
        super.onStop ();
        mapMapview.onStop ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        mapMapview.onDestroy ();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory ();
        mapMapview.onLowMemory ();
    }

    @Override
    public void onLocationChanged(Location location) {


        this.location = location;
        mapTvLocation.setText ( location.getLatitude ()+""+location.getLongitude () );
        setMyLocation ();
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

    @Override
    public void onClick(View view) {

    }
}
