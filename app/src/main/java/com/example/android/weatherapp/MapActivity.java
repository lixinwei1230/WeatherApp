package com.example.android.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.communication.loaders.ObservationsTask;
import com.hamweather.aeris.communication.loaders.ObservationsTaskCallback;
import com.hamweather.aeris.communication.parameter.PlaceParameter;
import com.hamweather.aeris.model.AerisError;

import java.util.List;

/**
 * Created by SuM_ on 12/7/15.
 */
public class MapActivity extends AppCompatActivity {

    private String lat;
    private String lon;
    private Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        extra = getIntent().getExtras();
        if (extra == null) {
            return;
        }
        // assign the values to string-arguments
        lon = extra.getString("lon");
        lat = extra.getString("lat");
        String myLoc = "lon:" + lon + "\t" + "lat:" + lat;

        Toast.makeText(getApplicationContext(), myLoc, Toast.LENGTH_LONG).show();

        AerisEngine.initWithKeys(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret), this);
        PlaceParameter place = new PlaceParameter(Float.parseFloat(lat), Float.parseFloat(lon));

        ObservationsTask task = new ObservationsTask(this.getApplicationContext(),
                new ObservationsTaskCallback() {

                    @Override
                    public void onObservationsFailed(AerisError error) {
                        // handle fail here
                        Toast.makeText(getApplicationContext(), "Connecting Map Failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onObservationsLoaded(List responses) {
                        // handle successful loading here.
                        Toast.makeText(getApplicationContext(), "Connecting Success", Toast.LENGTH_LONG).show();
                    }

                });

        task.requestClosest(place);


    }
}