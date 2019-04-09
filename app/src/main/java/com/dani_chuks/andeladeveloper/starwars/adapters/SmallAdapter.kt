package com.dani_chuks.andeladeveloper.starwars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dani_chuks.andeladeveloper.presentation_models.*
import com.dani_chuks.andeladeveloper.starwars.BR
import com.dani_chuks.andeladeveloper.starwars.R
import com.dani_chuks.andeladeveloper.starwars.databinding.*
import java.util.*

class SmallAdapter() : RecyclerView.Adapter<SmallAdapter.BaseViewHolder<ItemModel>>(), BindableAdapter<ItemModel> {

    private var itemModel: List<ItemModel>? = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<ItemModel> {
        when (viewType) {
            1 -> {
                val filmItemBinding = DataBindingUtil.inflate<SmallFilmItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_film_item, viewGroup, false)
                return FilmViewHolder(filmItemBinding.getRoot())
            }
            2 -> {
                val personItemBinding = DataBindingUtil.inflate<SmallPersonItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_person_item, viewGroup, false)
                return PersonViewHolder(personItemBinding.getRoot())
            }
            3 -> {
                val planetItemBinding = DataBindingUtil.inflate<SmallPlanetItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_planet_item, viewGroup, false)
                return PlanetViewHolder(planetItemBinding.getRoot())
            }
            4 -> {
                val smallSpecieItemBinding = DataBindingUtil.inflate<SmallSpecieItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_specie_item, viewGroup, false)
                return SpecieViewHolder(smallSpecieItemBinding.getRoot())
            }
            5 -> {
                val starshipItemBinding = DataBindingUtil.inflate<SmallStarshipItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_starship_item, viewGroup, false)
                return StarshipViewHolder(starshipItemBinding.getRoot())
            }
            else -> {
                val vehicleItemBinding = DataBindingUtil.inflate<SmallVehicleItemBinding>(LayoutInflater.from(viewGroup.context),
                        R.layout.small_vehicle_item, viewGroup, false)
                return VehicleViewHolder(vehicleItemBinding.getRoot())
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemModel>, position: Int) {
        itemModel?.get(position)?.let { holder.bind(it) }
    }

    override fun setItems(itemModel: List<ItemModel>?) {
        this.itemModel = itemModel
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return itemModel?.get(position)?.modelType ?: 6
    }

    override fun getItemCount(): Int {
        return itemModel?.size ?: 0
    }


    abstract inner class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(itemModel: T)

    }


    inner class FilmViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {
        internal var binding: SmallFilmItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? FilmModel)?.let {
                binding?.setVariable(BR.film, it)
                binding?.executePendingBindings()
            }
        }
    }


    inner class PersonViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {
        internal var binding: SmallPersonItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? PersonModel)?.let {
                binding?.setVariable(BR.person, it)
                binding?.executePendingBindings()
            }
        }
    }


    inner class PlanetViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {
        internal var binding: SmallPlanetItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? PlanetModel)?.let {
                binding?.setVariable(BR.planet, it)
                binding?.executePendingBindings()
            }
        }
    }


    inner class SpecieViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {
        internal var binding: SmallSpecieItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? SpecieModel)?.let {
                binding?.setVariable(BR.specie, it)
                binding?.executePendingBindings()
            }
        }
    }


    inner class StarshipViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {

        internal var binding: SmallStarshipItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? StarshipModel)?.let {
                binding?.setVariable(BR.starship, it)
                binding?.executePendingBindings()
            }
        }
    }


    inner class VehicleViewHolder<T>(itemView: View) : BaseViewHolder<T>(itemView) {

        internal var binding: SmallVehicleItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        override fun bind(itemModel: T) {
            (itemModel as? VehicleModel)?.let {
                binding?.setVariable(BR.vehicle, it)
                binding?.executePendingBindings()
            }
        }
    }
}
