package com.dani_chuks.andeladeveloper.starwars.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.dani_chuks.andeladeveloper.starwars.R
import com.dani_chuks.andeladeveloper.starwars.databinding.ActivityHomeBinding
import dagger.android.AndroidInjection
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    val viewContext: Context
        get() = this

    fun showAllFilms() {

    }

    fun showAllCharacters() {

    }

    fun showAllVehicles() {

    }

    fun showAllSpecies() {

    }

    fun showAllStarships() {

    }

    fun showFilm(filmId: Int) {

    }

    fun showCharacter(characterId: Int) {

    }

    fun showVehicle(vehicleId: Int) {

    }

    fun showSpecies(specieId: Int) {

    }

    fun showStarship(starshipId: Int) {

    }

    fun hideProgress(resourceName: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        val homeViewModel = ViewModelProviders.of(this, viewModelFactory).get<HomeViewModel>(HomeViewModel::class.java)
        binding.viewmodel = homeViewModel
        binding.setLifecycleOwner(this)
    }
}
