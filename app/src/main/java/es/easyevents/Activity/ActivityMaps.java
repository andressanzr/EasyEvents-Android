package es.easyevents.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

import es.easyevents.R;

public class ActivityMaps extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;
    private   Address address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.buscar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                //recoge los datos introducido en el buscador
                String locacion = searchView.getQuery().toString();
                //lista de los lugares
                List<Address> addressList = null;

                //verifica si hay datos introducidos
                if (locacion != null || !locacion.equals("")) {
                    Geocoder geocoder = new Geocoder(ActivityMaps.this);
                    try {
                        addressList = geocoder.getFromLocationName(locacion, 5);
                    } catch (IOException e) {

                        Log.d("errorMap", e.getMessage());
                        e.printStackTrace();
                    }
                    if(addressList.size()>0){
                        address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLng).title(locacion));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }
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
//Llama al googlemap
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }

//Devuelve la variableen el editText, insertada en el buscador
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ActivityMaps.this, EventoCreando.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                if (address != null) {
                    intent.putExtra("address", address.getAddressLine(0));
                    setResult(RESULT_OK, intent);
                }
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
