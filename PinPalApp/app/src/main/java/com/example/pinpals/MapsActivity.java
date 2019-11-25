package com.example.pinpals;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.widget.TextView;

import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG = MapsActivity.class.getSimpleName();

    private TextView lat;
    private TextView lon;
    private TextView time;
    double longitude;
    double latitude;
    Date date;

    //test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Intent intent = getIntent();
//        String timeString = intent.getStringExtra("time");
//        longitude = intent.getDoubleExtra("longitude", 0);
//        latidtude = intent.getDoubleExtra("latitude", 0);
//        Log.i("Intent",latidtude+""+longitude);
        latitude = 5999994.0;
        longitude = 8849993.9;
        date = new Date();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        lat = findViewById(R.id.lat);
        lat.setText("Latitude: " + latitude);
        lon = findViewById(R.id.lon);
        lon.setText("Longitude: " + longitude);
        time = findViewById(R.id.time);
        time.setText("Time: " + date);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.e(TAG, "In Map");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(53.350140, -6.266155);
        mMap.addMarker(new MarkerOptions().position(sydney).title("PinPal Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
