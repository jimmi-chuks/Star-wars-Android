package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;

import java.util.List;

public class SpecieAdapterSmall extends RecyclerView.Adapter<SpecieAdapterSmall.MyViewHolder> {

    List<Specie> species;

    public SpecieAdapterSmall(final List<Specie> specie) {
        this.species = specie;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_specie_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        Specie specie = species.get(position);
        String name = myViewHolder.context.getString(R.string.card_title, specie.getName());
        String classification = myViewHolder.context.getString(R.string.card_classification, specie.getClassification());
        String language = myViewHolder.context.getString(R.string.card_language, specie.getClassification());
        String designation = myViewHolder.context.getString(R.string.card_designation, specie.getDesignation());

        myViewHolder.name.setText(name);
        myViewHolder.classification.setText(classification);
        myViewHolder.language.setText(language);
        myViewHolder.designation.setText(designation);
    }

    public void setSpecies(List<Specie> species){
        this.species = species;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return species.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView classification;
        TextView language;
        TextView designation;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            classification = itemView.findViewById(R.id.classification);
            language = itemView.findViewById(R.id.language);
            designation = itemView.findViewById(R.id.designation);
            context = itemView.getContext();
        }
    }
}
