package com.example.pinpals;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG = MapsActivity.class.getSimpleName();
    private TextView lat;
    private TextView lon;
    private TextView time;
    double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        int reg = intent.getIntExtra("reg", 0);
        String timeString = intent.getStringExtra("time");
        longitude = intent.getDoubleExtra("longitude", 0);
        latitude = intent.getDoubleExtra("latitude", 0);
        if (intent.getExtras().containsKey("sos")) {
            Toast.makeText(MapsActivity.this, "PinPal " + reg + " update!!!", Toast.LENGTH_LONG).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lat = findViewById(R.id.lat);
        lat.setText("Latitude: " + latitude);
        lon = findViewById(R.id.lon);
        lon.setText("Longitude: " + longitude);
        time = findViewById(R.id.time);
        time.setText("Time: " + timeString);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng dublin = new LatLng(latitude, longitude); //lat,lng
        mMap.addMarker(new MarkerOptions().position(dublin).title("PinPal Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));
    }

}
