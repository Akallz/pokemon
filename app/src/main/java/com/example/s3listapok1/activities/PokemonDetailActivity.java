package com.example.s3listapok1.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s3listapok1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class PokemonDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        // Recuperar los datos del Intent
        String pokemonName = getIntent().getStringExtra("pokemon_name");
        String pokemonType = getIntent().getStringExtra("pokemon_type");

        // Mostrar los datos en la interfaz de usuario
        TextView tvPokemonName = findViewById(R.id.tvPokemonName);
        TextView tvPokemonType = findViewById(R.id.tvPokemonType);

        tvPokemonName.setText(pokemonName);
        tvPokemonType.setText(pokemonType);

        // Configurar el MapView
        mapView = findViewById(R.id.mapView);

        // Inicializar el MapView
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Generar coordenadas aleatorias
        LatLng randomLocation = getRandomLocation();

        // Añadir un marcador en la ubicación aleatoria y mover la cámara
        googleMap.addMarker(new MarkerOptions().position(randomLocation).title("Ubicación aleatoria"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(randomLocation, 3)); // Zoom 3 para ver una región amplia
    }

    // Método para generar coordenadas aleatorias
    private LatLng getRandomLocation() {
        Random random = new Random();

        // Generar latitud aleatoria (-90 a 90)
        double latitude = -90 + (90 - (-90)) * random.nextDouble();

        // Generar longitud aleatoria (-180 a 180)
        double longitude = -180 + (180 - (-180)) * random.nextDouble();

        return new LatLng(latitude, longitude);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
}
