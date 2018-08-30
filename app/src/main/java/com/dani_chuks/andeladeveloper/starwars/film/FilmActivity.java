package com.dani_chuks.andeladeveloper.starwars.film;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.adapters.FilmAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.SpecieAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.StarshipAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.VehicleAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class FilmActivity extends AppCompatActivity implements FilmView {

    Group filmGroup;
    Group starshipGroup;
    Group specieGroup;
    Group vehicleGroup;

    TextView filmTextview;
    TextView vehicleTextview;
    TextView specieTextview;
    TextView starshipTextview;

    RecyclerView filmRecyclerView;
    RecyclerView vehicleRecyclerView;
    RecyclerView specieRecyclerView;
    RecyclerView starshipRecyclerView;
    private FilmAdapterSmall filmAdapter;
    private SpecieAdapterSmall specieAdapter;
    private VehicleAdapterSmall vehicleAdapter;
    private StarshipAdapterSmall starshipAdapter;

    @Override
    public void showFilms(final List<Film> films) {

    }

    @Override
    public void showVehicles(final List<Vehicle> vehicles) {

    }

    @Override
    public void showSpecies(final List<Specie> species) {

    }

    @Override
    public void showStarships(final List<Starship> starships) {

    }

    @Override
    public void hideStarShipGroup() {

    }

    @Override
    public void hidefilmGroup() {

    }

    @Override
    public void hideSpeciesGroup() {

    }

    @Override
    public void hideStarshipGroup() {

    }

    public void initializeView() {
        filmRecyclerView = findViewById(R.id.films_recyclerView);
        specieRecyclerView = findViewById(R.id.species_recyclerView);
        vehicleRecyclerView = findViewById(R.id.vehicles_recyclerView);
        starshipRecyclerView = findViewById(R.id.starships_recyclerView);

        filmAdapter = new FilmAdapterSmall(new ArrayList<>());
        specieAdapter = new SpecieAdapterSmall(new ArrayList<>());
        vehicleAdapter = new VehicleAdapterSmall(new ArrayList<>());
        starshipAdapter = new StarshipAdapterSmall(new ArrayList<>());

        setHorizontalLayoutManager(specieRecyclerView);
        setHorizontalLayoutManager(starshipRecyclerView);
        setHorizontalLayoutManager(vehicleRecyclerView);
        setHorizontalLayoutManager(filmRecyclerView);

        specieRecyclerView.setAdapter(specieAdapter);
        starshipRecyclerView.setAdapter(starshipAdapter);
        vehicleRecyclerView.setAdapter(vehicleAdapter);
        filmRecyclerView.setAdapter(filmAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        if(savedInstanceState != null){
           String filmUrl = getIntent().getExtras().getString("key");

        }
    }

    public void setHorizontalLayoutManager(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
