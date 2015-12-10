package com.example.android.weatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by SuM_ on 12/7/15.
 */
public class MapActivity extends FragmentActivity {

    private String myLat;
    private String myLon;
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
        myLon = extra.getString("lon");
        myLat = extra.getString("lat");
        String myLoc = "lon:" + myLon + "\t" + "lat:" + myLat;

        //Toast.makeText(getApplicationContext(), myLoc, Toast.LENGTH_LONG).show();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyFragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putString("lat", myLat);
        bundle.putString("lng", myLon);

        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.myPlaceholder, fragment);
        fragmentTransaction.commit();

    }


}