package com.example.s3listapok1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3listapok1.activities.CreatePokemonActivity;
import com.example.s3listapok1.adapters.PokemonAdaptar;
import com.example.s3listapok1.entities.ApiClient;
import com.example.s3listapok1.entities.Pokemon;
import com.example.s3listapok1.interfaces.PokemonApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvPokemon = findViewById(R.id.rvPokemon);
        rvPokemon.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this,CreatePokemonActivity.class);
                    startActivity(intent);
                }
        );
        // Cliente Retrofit y la interfaz

        PokemonApiService apiService = ApiClient.getClient().create(PokemonApiService.class);

        // Llamada a la API

        Call<List<Pokemon>> call = apiService.getPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if(response.isSuccessful()){
                    List<Pokemon> pokemons = response.body();

                    PokemonAdaptar adaptar = new PokemonAdaptar(pokemons);
                    rvPokemon.setAdapter(adaptar);
                }else{
                    Toast.makeText(MainActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable throwable) {
                Log.e("API Error","Error en la llamada a la API");
                Toast.makeText(MainActivity.this,"Fallo en la conexion",Toast.LENGTH_SHORT).show();
            }
        });
    }
}