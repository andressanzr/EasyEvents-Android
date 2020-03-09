package es.easyevents;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ActivityMaps extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        searchView = findViewById(R.id.buscar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String locacion = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (locacion != null || !locacion.equals("")) {
                    Geocoder geocoder = new Geocoder(ActivityMaps.this);
                    try {
                        addressList = geocoder.getFromLocationName(locacion, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(locacion));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }
}
