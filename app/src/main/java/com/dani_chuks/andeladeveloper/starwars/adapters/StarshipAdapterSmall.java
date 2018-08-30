package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;

import java.util.List;

public class StarshipAdapterSmall extends RecyclerView.Adapter<StarshipAdapterSmall.MyViewHolder> {

    List<Starship> starships;

    public StarshipAdapterSmall(final List<Starship> starship) {
        this.starships = starship;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_starship_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        Starship starship = starships.get(position);
        String name = myViewHolder.context.getString(R.string.card_title, starship.getName());
        String crew = myViewHolder.context.getString(R.string.card_crew, starship.getCrew());
        String passenger = myViewHolder.context.getString(R.string.card_passenger, starship.getPassengers());
        String hyperdriveRating = myViewHolder.context.getString(R.string.card_hyper_drive_rating, starship.getName());
        myViewHolder.name.setText(name);
        myViewHolder.crew.setText(crew);
        myViewHolder.passenger.setText(passenger);
        myViewHolder.hyperdriveRating.setText(hyperdriveRating);
    }

    public void setStarships(List<Starship> starships){
        this.starships = starships;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return starships.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView crew;
        TextView passenger;
        TextView hyperdriveRating;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            crew = itemView.findViewById(R.id.crew);
            passenger = itemView.findViewById(R.id.passenger);
            hyperdriveRating = itemView.findViewById(R.id.hyperdrive_rating);
            context = itemView.getContext();
        }


    }
}
