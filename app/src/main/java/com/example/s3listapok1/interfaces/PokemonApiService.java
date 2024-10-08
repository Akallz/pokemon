package com.example.s3listapok1.interfaces;



import com.example.s3listapok1.entities.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PokemonApiService {
    @GET("Informacion/pokemon")
    Call<List<Pokemon>> getPokemons();

    @POST("Informacion/pokemon")
    Call<Pokemon> createPokemon(@Body Pokemon pokemon);
}
