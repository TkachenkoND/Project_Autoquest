package com.example.GPS;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.StartQuest.FirstCoordinate;
import com.example.StartQuest.TheSecondTask;
import com.example.project_aq_version009.Coordinate;

public class MyLocationListener extends AppCompatActivity implements LocationListener {

    public static Location imHere;

    @SuppressLint("MissingPermission")
    public static void SetUpLocationListener(Context context)
    {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener();

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                1,
                locationListener);

        imHere = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onLocationChanged(Location loc) {

    }
    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public static boolean checkPosition(Location location, double latitude, double longitude){
        if(Math.pow((location.getLatitude() - latitude),2) + Math.pow((location.getLongitude() - longitude),2) == 0.0000001
                || Math.pow((location.getLatitude() - latitude),2) + Math.pow((location.getLongitude() -longitude),2) < 0.0000001 ){
            return true;
        } else
            return false;

    }



}
