package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;

import java.util.List;

public class PlanetAdapterSmall extends RecyclerView.Adapter<PlanetAdapterSmall.MyViewHolder> {

    List<Planet> planets;

    public PlanetAdapterSmall(final List<Planet> planets) {
        this.planets = planets;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_planet_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        bindPlanet(myViewHolder, position);
    }

    private void bindPlanet(final @NonNull MyViewHolder myViewHolder, final int position) {
        Planet planet = planets.get(position);
        String name = myViewHolder.context.getString(R.string.card_title, planet.getName());
        String diameter = myViewHolder.context.getString(R.string.card_classification, planet.getDiameter());
        String gravity = myViewHolder.context.getString(R.string.card_language, planet.getGravity());
        String terrain = myViewHolder.context.getString(R.string.card_terrain, planet.getGravity());
        myViewHolder.name.setText(name);
        myViewHolder.diameter.setText(diameter);
        myViewHolder.gravity.setText(gravity);
        myViewHolder.terrain.setText(terrain);
    }

    public void setPlanets(List<Planet> planets){
        this.planets = planets;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView diameter;
        TextView gravity;
        TextView terrain;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            diameter = itemView.findViewById(R.id.diameter);
            gravity = itemView.findViewById(R.id.gravity);
            terrain = itemView.findViewById(R.id.terrain);
            context = itemView.getContext();
        }


    }
}
