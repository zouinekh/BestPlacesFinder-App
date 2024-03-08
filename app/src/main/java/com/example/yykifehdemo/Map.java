package com.example.yykifehdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.yykifehdemo.directionhelpers.FetchURL;
import com.example.yykifehdemo.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap myMap;
    private Polyline currentPolyLine;

    private LatLng userLocation,destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        Double userLatitude=intent.getDoubleExtra("currentLatitude",36.6959);
        Double userLongitude=intent.getDoubleExtra("currentLongitude",10.1647);
        Double placeLatitude=intent.getDoubleExtra("DestinationLatitude",0);
        Double placeLongitude=intent.getDoubleExtra("DestinationLongitude",0);
        userLocation = new LatLng(userLatitude,userLongitude);
        destination = new LatLng(placeLatitude,placeLongitude);

        System.out.println(Double.toString(placeLatitude));
        String url=getUrl(userLocation,destination,"driving");
        new FetchURL(Map.this).execute(url,"driving");
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;

        googleMap.addMarker(new MarkerOptions().position(userLocation).title(getString(R.string.currentLocation)));
        googleMap.addMarker(new MarkerOptions().position(destination).title(getString(R.string.destination)));
        float zoomLevel = 12.0f;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,zoomLevel));

    }

    @Override
    public void onTaskDone(Object... values) {

        if (myMap != null) {
            if (currentPolyLine != null) {
                currentPolyLine.remove();
            }
            currentPolyLine = myMap.addPolyline((PolylineOptions) values[0]);
        } else {
            Log.d("aaaaaaaaaaaaaaa", values[0].toString());
        }
    }

}