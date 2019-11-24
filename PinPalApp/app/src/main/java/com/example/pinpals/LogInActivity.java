package com.example.pinpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LogInActivity.this, MapsActivity.class);
        intent.putExtra("time", "1234");
        intent.putExtra("longitude", -6.266155);
        intent.putExtra("latitude",  53.350140);
        startActivity(intent);

        Log.i("response", "???");
//        OkHttpClient client = new OkHttpClient();
//        FormBody.Builder formBuilder = new FormBody.Builder();
//        formBuilder.add("user_id", Integer.toString(1));
//        Request request = new Request.Builder().url("http://10.0.2.2:5000/latest").post(formBuilder.build()).build();
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
//                                Toast.makeText(LogInActivity.this, "Fail to connect the server", Toast.LENGTH_SHORT).show();
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
//                final HashMap<Integer, PinPal> PinPals = new HashMap<Integer, PinPal>();
//                try {
//
//                    JSONArray jsonArray = new JSONArray(res);
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                        if (jsonObject != null) {
//                            int reg = jsonObject.optInt("reg_code");
//                            String time = jsonObject.optString("time");
//                            double lat = jsonObject.optDouble("latitude");
//                            double lng = jsonObject.optDouble("longitude");
//                            // Package PinPal Object
//                            PinPal pinPal = new PinPal(reg, time, lat, lng);
//                            PinPals.put(pinPal.getReg(), pinPal);
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("Hiiiiiiiii",PinPals.get(123456).getReg()+"");
//                    }
//                });
//
//            }
//        });
    }
}
