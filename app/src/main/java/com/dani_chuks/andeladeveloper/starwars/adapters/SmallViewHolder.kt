package com.dani_chuks.andeladeveloper.starwars.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*
import com.dani_chuks.andeladeveloper.starwars.BR
import com.dani_chuks.andeladeveloper.starwars.databinding.*

abstract class SmallViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(itemModel: T)

}

class FilmViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallFilmItemBinding? =  DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? FilmModel)?.let {
            binding?.setVariable(BR.film, it)
            binding?.executePendingBindings()
        }
    }
}


class PersonViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallPersonItemBinding? = DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? PersonModel)?.let {
            binding?.setVariable(BR.person, it)
            binding?.executePendingBindings()
        }
    }
}


class PlanetViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallPlanetItemBinding? = DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? PlanetModel)?.let {
            binding?.setVariable(BR.planet, it)
            binding?.executePendingBindings()
        }
    }
}

class SpecieViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallSpecieItemBinding? = DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? SpecieModel)?.let {
            binding?.setVariable(BR.specie, it)
            binding?.executePendingBindings()
        }
    }
}


class StarshipViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallStarshipItemBinding? = DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? StarshipModel)?.let {
            binding?.setVariable(BR.starship, it)
            binding?.executePendingBindings()
        }
    }
}


class VehicleViewHolder<T>(itemView: View) : SmallViewHolder<T>(itemView) {

    val binding: SmallVehicleItemBinding? = DataBindingUtil.bind(itemView)

    override fun bind(itemModel: T) {
        (itemModel as? VehicleModel)?.let {
            binding?.setVariable(BR.vehicle, it)
            binding?.executePendingBindings()
        }
    }
}