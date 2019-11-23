package com.example.pinpals;

import androidx.fragment.app.FragmentActivity;

import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        String url = "http://10.0.2.2:5000/latest";
        SendMessage(url,1);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng dublin = new LatLng(53.350140, -6.266155); //lat,lng
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(dublin).title("Marker in Dublin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));
    }

    private void SendMessage(String url, int user_id) {

        final HashMap<Integer, PinPal> PinPals = new HashMap<Integer, PinPal>();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("user_id", Integer.toString(user_id));
        Request request = new Request.Builder().url(url).post(formBuilder.build()).build();
        Call call = client.newCall(request);
        try {

            Response response = call.execute();
            Log.i("TAG","!!!!!!!!!!!!!!!!!!!");
            //判断请求是否成功
            if(response.isSuccessful()) {
                String res = response.body().string();
                response.body().close();
                Log.i("TAG",res);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

//        OkHttpClient client = new OkHttpClient();
//        FormBody.Builder formBuilder = new FormBody.Builder();
//        formBuilder.add("user_id", Integer.toString(user_id));
//        Request request = new Request.Builder().url(url).post(formBuilder.build()).build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MapsActivity.this, "Fail to connect the server", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                final String res = response.body().string();
//                Log.i("response", res);
//                try {
//                    JSONArray jsonArray = new JSONArray(res);
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                        if (jsonObject != null) {
//                            int reg = jsonObject.optInt("reg_code");
//
//                            String time = jsonObject.optString("time");
//
//                            double lat = jsonObject.optDouble("latitude");
//
//                            double lng = jsonObject.optDouble("longitude");
//
//                            // Package PinPal Object
//                            PinPal pinPal = new PinPal(reg, time, lat, lng);
//                            PinPals.put(pinPal.getReg(), pinPal);
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("almost", PinPals.get(123456).getReg() + " " + PinPals.get(123456).getLocation()[0]);
//                    }
//                });
//            }
//
//        });
    }

}
