package com.example.s3listapok1.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s3listapok1.R;
import com.example.s3listapok1.entities.ApiClient;
import com.example.s3listapok1.entities.Pokemon;
import com.example.s3listapok1.interfaces.PokemonApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePokemonActivity extends AppCompatActivity {
    private EditText etName, etHabilidad;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pokemon);

        etName = findViewById(R.id.etName);
        etHabilidad = findViewById(R.id.etHabilidad);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String habilidad = etHabilidad.getText().toString();

            if (!name.isEmpty() && !habilidad.isEmpty()) {
                // Crear un nuevo objeto Pokémon
                Pokemon newPokemon = new Pokemon(name, habilidad);
                // Llamar a la API para agregarlo
                savePokemonToApi(newPokemon);
            } else {
                Toast.makeText(CreatePokemonActivity.this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePokemonToApi(Pokemon pokemon) {
        PokemonApiService apiService = ApiClient.getClient().create(PokemonApiService.class);
        Call<Pokemon> call = apiService.createPokemon(pokemon);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreatePokemonActivity.this, "Pokémon creado exitosamente", Toast.LENGTH_SHORT).show();
                    finish(); // Cierra la actividad después de crear el Pokémon
                } else {
                    Toast.makeText(CreatePokemonActivity.this, "Error al crear el Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(CreatePokemonActivity.this, "Fallo en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
