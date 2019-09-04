package com.nightmaredevs.panicbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity  {
    TextView textView;

    private FusedLocationProviderClient fusedLocationClient;

    private void verifyPermission(){
        String [] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),permissions[0])!= PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
    }

    private void tracklocation(){
        textView = (TextView)findViewById(R.id.textView);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                           textView.setText("Latitud "+location.getLatitude()+"  Longitud "+location.getLongitude());
                        }
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyPermission();
        tracklocation();

    }
}
