package com.example.s3listapok1.adapters;
import com.example.s3listapok1.R;
import com.example.s3listapok1.activities.PokemonDetailActivity;
import com.example.s3listapok1.entities.Pokemon;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class PokemonAdaptar extends RecyclerView.Adapter<PokemonAdaptar.ContactViewHolder>{

    private List<Pokemon> data;

    public PokemonAdaptar(List<Pokemon> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pokemon, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        View view = holder.itemView;
        Pokemon item = data.get(position);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvType = view.findViewById(R.id.tvType);

        tvName.setText(item.getName());
        tvType.setText(item.getHabilidad());

        // Configurar el OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear Intent para iniciar la nueva actividad
                Intent intent = new Intent(v.getContext(), PokemonDetailActivity.class);
                // Pasar los datos del Pokémon seleccionado
                intent.putExtra("pokemon_name", item.getName());
                intent.putExtra("pokemon_type", item.getHabilidad());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
