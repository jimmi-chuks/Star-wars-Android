package com.dani_chuks.andeladeveloper.starwars.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType.*
import com.dani_chuks.andeladeveloper.presentation_models.MainModels
import com.dani_chuks.andeladeveloper.starwars.R
import com.dani_chuks.andeladeveloper.starwars.databinding.*

class SmallAdapter : ListAdapter<MainModels, SmallViewHolder<MainModels>>(MainModelDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SmallViewHolder<MainModels> {

        when (viewTypeFromInt(viewType)) {
            FILM -> {
                val filmItemBinding = DataBindingUtil.inflate<SmallFilmItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_film_item, viewGroup, false)
                return FilmViewHolder(filmItemBinding.root)
            }

            PERSON -> {
                val personItemBinding = DataBindingUtil.inflate<SmallPersonItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_person_item, viewGroup, false)
                return PersonViewHolder(personItemBinding.root)
            }

            PLANET -> {
                val planetItemBinding = DataBindingUtil.inflate<SmallPlanetItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_planet_item, viewGroup, false)
                return PlanetViewHolder(planetItemBinding.root)
            }

            SPECIE -> {
                val smallSpecieItemBinding = DataBindingUtil.inflate<SmallSpecieItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_specie_item, viewGroup, false)
                return SpecieViewHolder(smallSpecieItemBinding.root)
            }

            STARSHIP -> {
                val starshipItemBinding = DataBindingUtil.inflate<SmallStarshipItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_starship_item, viewGroup, false)
                return StarshipViewHolder(starshipItemBinding.root)
            }
            VEHICLE -> {
                val vehicleItemBinding = DataBindingUtil.inflate<SmallVehicleItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_vehicle_item, viewGroup, false)
                return VehicleViewHolder(vehicleItemBinding.root)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position)?.modelType?.ordinal?: 0

    fun viewTypeFromInt(viewType: Int): ItemModelType = values()[viewType]

    override fun onBindViewHolder(holder: SmallViewHolder<MainModels>, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainModelDiffCallback : DiffUtil.ItemCallback<MainModels>() {
    override fun areItemsTheSame(oldItem: MainModels, newItem: MainModels): Boolean {
        return oldItem.url == newItem.url && oldItem.modelType == newItem.modelType
    }

    override fun areContentsTheSame(oldItem: MainModels, newItem: MainModels) = oldItem == newItem
}
