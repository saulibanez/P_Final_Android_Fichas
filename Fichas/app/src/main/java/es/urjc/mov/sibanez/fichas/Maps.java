package es.urjc.mov.sibanez.fichas;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.urjc.mov.sibanez.fichas_v0.R;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private float latitude, longitude;
    private String zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String lat = getIntent().getExtras().getString("latitude");
        String lon = getIntent().getExtras().getString("longitude");
        zone = getIntent().getExtras().getString("zone");
        latitude = Float.parseFloat(lat);
        longitude = Float.parseFloat(lon);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(zone));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}