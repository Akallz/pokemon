package com.example.s3listapok1.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s3listapok1.R;

public class PokemonDetailActivity extends AppCompatActivity {

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
    }
}

