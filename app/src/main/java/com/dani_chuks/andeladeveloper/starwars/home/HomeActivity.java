package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.adapters.FilmAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.PersonAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.PlanetAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.SpecieAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.StarshipAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.adapters.VehicleAdapterSmall;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class HomeActivity extends AppCompatActivity implements HomeView {

    @Inject
    HomeViewModelFactory viewModelFactory;
    @Inject
    HomePresenter presenter;

    RecyclerView filmsRecyclerView;
    RecyclerView planetsRecyclerView;
    RecyclerView speciesRecyclerView;
    RecyclerView vehiclesRecyclerView;
    RecyclerView starshipsRecyclerView;
    RecyclerView peopleRecyclerView;
    ProgressBar filmsProgressBar;
    ProgressBar speciesProgressBar;
    ProgressBar planetsProgressBar;
    ProgressBar starshipProgressBar;
    ProgressBar vehicleProgressBar;
    ProgressBar peopleProgressBar;
    private FilmAdapterSmall filmAdapter;
    private PersonAdapterSmall personAdapter;
    private PlanetAdapterSmall planetAdapter;
    private StarshipAdapterSmall starshipAdapter;
    private SpecieAdapterSmall specieAdapter;
    private VehicleAdapterSmall vehicleAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void showAllFilms() {

    }

    @Override
    public void showAllCharacters() {

    }

    @Override
    public void showAllVehicles() {

    }

    @Override
    public void showAllSpecies() {

    }

    @Override
    public void showAllStarships() {

    }

    @Override
    public void showFilm(final int filmId) {

    }

    @Override
    public void showCharacter(final int characterId) {

    }

    @Override
    public void showVehicle(final int vehicleId) {

    }

    @Override
    public void showSpecies(final int specieId) {

    }

    @Override
    public void showStarship(final int starshipId) {

    }

    @Override
    public void hideProgress(final String resourceName) {

    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void initializeView() {
        filmsRecyclerView = findViewById(R.id.films_recyclerView);
        planetsRecyclerView = findViewById(R.id.planets_recyclerView);
        speciesRecyclerView = findViewById(R.id.species_recyclerView);
        peopleRecyclerView = findViewById(R.id.people_recyclerView);
        vehiclesRecyclerView = findViewById(R.id.vehicles_recyclerView);
        starshipsRecyclerView = findViewById(R.id.starships_recyclerView);

        filmsProgressBar = findViewById(R.id.films_progress_view);
        planetsProgressBar = findViewById(R.id.planet_progress_view);
        speciesProgressBar = findViewById(R.id.species_progress_view);
        peopleProgressBar = findViewById(R.id.people_progress_view);
        vehicleProgressBar = findViewById(R.id.vehicle_progress_view);
        starshipProgressBar = findViewById(R.id.starship_progress_view);

        filmAdapter = new FilmAdapterSmall(new ArrayList<>());
        planetAdapter = new PlanetAdapterSmall(new ArrayList<>());
        specieAdapter = new SpecieAdapterSmall(new ArrayList<>());
        vehicleAdapter = new VehicleAdapterSmall(new ArrayList<>());
        starshipAdapter = new StarshipAdapterSmall(new ArrayList<>());
        personAdapter = new PersonAdapterSmall(new ArrayList<>());

        setHorizontalLayoutManager(peopleRecyclerView);
        setHorizontalLayoutManager(planetsRecyclerView);
        setHorizontalLayoutManager(speciesRecyclerView);
        setHorizontalLayoutManager(starshipsRecyclerView);
        setHorizontalLayoutManager(vehiclesRecyclerView);
        setHorizontalLayoutManager(filmsRecyclerView);

        peopleRecyclerView.setAdapter(personAdapter);
        planetsRecyclerView.setAdapter(planetAdapter);
        speciesRecyclerView.setAdapter(specieAdapter);
        starshipsRecyclerView.setAdapter(starshipAdapter);
        vehiclesRecyclerView.setAdapter(vehicleAdapter);
        filmsRecyclerView.setAdapter(filmAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeView();
        initializeViewModel();
        presenter.initView(this);
        presenter.fetchData();
    }

    private void initializeViewModel() {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        final Observer<List<Film>> filmListObserver = results -> {
            filmAdapter.setFilms(results);
            hideView(filmsProgressBar);
        };
        homeViewModel.getFilms().observe(this, filmListObserver);
        final Observer<List<Person>> peopleObserver = results -> {
            personAdapter.setPeope(results);
            hideView(peopleProgressBar);
        };
        homeViewModel.getPeople().observe(this, peopleObserver);
        final Observer<List<Planet>> planetListObserver = results -> {
            planetAdapter.setPlanets(results);
            hideView(planetsProgressBar);
        };
        homeViewModel.getPlanets().observe(this, planetListObserver);
        final Observer<List<Vehicle>> vehicleListObserver = results -> {
            vehicleAdapter.setVehicles(results);
            hideView(vehicleProgressBar);
        };
        homeViewModel.getVehicles().observe(this, vehicleListObserver);
        final Observer<List<Specie>> specieListObserver = results -> {
            specieAdapter.setSpecies(results);
            hideView(speciesProgressBar);
        };
        homeViewModel.getSpecies().observe(this, specieListObserver);
        final Observer<List<Starship>> starshipListObserver = results -> {
            starshipAdapter.setStarships(results);
            hideView(starshipProgressBar);
        };
        homeViewModel.getStarships().observe(this, starshipListObserver);
    }

    public void hideView(View view){
        view.setVisibility(View.GONE);
    }

    public void setHorizontalLayoutManager(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}
