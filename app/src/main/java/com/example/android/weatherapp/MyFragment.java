package com.example.android.weatherapp;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamweather.aeris.communication.AerisCallback;
import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.communication.EndpointType;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.MapViewFragment;
import com.hamweather.aeris.maps.interfaces.OnAerisMapLongClickListener;
import com.hamweather.aeris.model.AerisResponse;
import com.hamweather.aeris.tiles.AerisTile;

/**
 * Created by SuM_ on 12/8/15.
 */
public class MyFragment extends MapViewFragment implements
        OnAerisMapLongClickListener, AerisCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AerisEngine.initWithKeys(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret), "usc.forecast.weather");

        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        mapView = (AerisMapView) view.findViewById(R.id.aerisfragment_map);
        mapView.init(savedInstanceState, AerisMapView.AerisMapType.GOOGLE);

        Bundle bundle = getArguments();
        String myLat = bundle.getString("lat");
        String myLng = bundle.getString("lng");

        Location location = new Location("");
        location.setLatitude(Double.valueOf(myLat));
        location.setLongitude(Double.valueOf(myLng));

        mapView.setZoomControlsEnabled(true);
        mapView.moveToLocation(location, 6);
        mapView.addLayer(AerisTile.RADSAT);

        mapView.setOnAerisMapLongClickListener(this);

        return view;
    }

    @Override
    public void onMapLongClick(double lat, double longitude) {
        // code to handle map long press. i.e. Fetch current conditions?
        // see demo app MapFragment.java
    }

    @Override
    public void onResult(EndpointType type, AerisResponse response) {

    }
}
