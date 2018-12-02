package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.databinding.ActivityHomeBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class HomeActivity extends AppCompatActivity {

    @Inject
    HomeViewModelFactory viewModelFactory;

    public void showAllFilms() {

    }

    public void showAllCharacters() {

    }

    public void showAllVehicles() {

    }

    public void showAllSpecies() {

    }

    public void showAllStarships() {

    }

    public void showFilm(final int filmId) {

    }

    public void showCharacter(final int characterId) {

    }

    public void showVehicle(final int vehicleId) {

    }

    public void showSpecies(final int specieId) {

    }

    public void showStarship(final int starshipId) {

    }

    public void hideProgress(final String resourceName) {

    }

    public Context getViewContext() {
        return this;
    }

    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
//        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        HomeViewModel homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
//        homeViewModel.initItems();
        binding.setViewmodel(homeViewModel);
        binding.setLifecycleOwner(this);
    }
}
