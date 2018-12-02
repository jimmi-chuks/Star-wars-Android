package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.presentation_models.ItemModel;
import com.dani_chuks.andeladeveloper.presentation_models.PersonModel;
import com.dani_chuks.andeladeveloper.presentation_models.PlanetModel;
import com.dani_chuks.andeladeveloper.presentation_models.SpecieModel;
import com.dani_chuks.andeladeveloper.presentation_models.StarshipModel;
import com.dani_chuks.andeladeveloper.presentation_models.VehicleModel;
import com.dani_chuks.andeladeveloper.starwars.BR;
import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallFilmItemBinding;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallPersonItemBinding;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallPlanetItemBinding;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallSpecieItemBinding;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallStarshipItemBinding;
import com.dani_chuks.andeladeveloper.starwars.databinding.SmallVehicleItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SmallAdapter<T extends ItemModel> extends RecyclerView.Adapter<SmallAdapter.BaseViewHolder> implements BindableAdapter {

    private List<T> itemModel = new ArrayList<>();

    public SmallAdapter() {
    }

    public SmallAdapter(List<T> list){
        this.itemModel = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        switch (viewType) {
            case 1:
                SmallFilmItemBinding filmItemBinding  = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.small_film_item,
                        viewGroup, false);
                return new FilmViewHolder(filmItemBinding.getRoot());
            case 2:
                SmallPersonItemBinding personItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.small_person_item, viewGroup, false);
                return new PersonViewHolder(personItemBinding.getRoot());
            case 3:
                SmallPlanetItemBinding planetItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.small_planet_item, viewGroup, false);
                return new PlanetViewHolder(planetItemBinding.getRoot());
            case 4:
                SmallSpecieItemBinding smallSpecieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.small_specie_item, viewGroup, false);
                return new SpecieViewHolder(smallSpecieItemBinding.getRoot());
            case 5:
                SmallStarshipItemBinding starshipItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.small_starship_item, viewGroup, false);
                return new StarshipViewHolder(starshipItemBinding.getRoot());
            default:
                SmallVehicleItemBinding vehicleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.small_vehicle_item, viewGroup, false);
                return new VehicleViewHolder(vehicleItemBinding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder baseViewHolder, final int position) {
        ItemModel model = itemModel.get(position);
        baseViewHolder.bind(model);
    }

    @Override
    public void setItems(final List itemModel) {
        this.itemModel = itemModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return itemModel.get(position).getModelType();
    }

    @Override
    public int getItemCount() {
        int itemSize = null != itemModel ? itemModel.size() : 0;
        return itemSize;
    }


    public abstract class BaseViewHolder<T extends ItemModel> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bind(T object);
    }


    public class FilmViewHolder extends BaseViewHolder<FilmModel> {
        SmallFilmItemBinding binding;

        public FilmViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final FilmModel film) {
            binding.setVariable(BR.film, film);
            binding.executePendingBindings();
        }
    }


    public class PersonViewHolder extends BaseViewHolder<PersonModel> {
        SmallPlanetItemBinding binding;

        public PersonViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final PersonModel person) {
            binding.setVariable(BR.person, person);
            binding.executePendingBindings();
        }
    }


    public class PlanetViewHolder extends BaseViewHolder<PlanetModel> {
        SmallPlanetItemBinding binding;

        public PlanetViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final PlanetModel planet) {
            binding.setVariable(BR.planet, planet);
            binding.executePendingBindings();
        }
    }


    public class SpecieViewHolder extends BaseViewHolder<SpecieModel> {
        SmallSpecieItemBinding binding;

        public SpecieViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final SpecieModel specie) {
            binding.setVariable(BR.specie, specie);
            binding.executePendingBindings();
        }
    }


    public class StarshipViewHolder extends BaseViewHolder<StarshipModel> {

        SmallStarshipItemBinding binding;

        public StarshipViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final StarshipModel starship) {
            binding.setVariable(BR.starship, starship);
            binding.executePendingBindings();
        }
    }


    public class VehicleViewHolder extends BaseViewHolder<VehicleModel> {

        SmallVehicleItemBinding binding;

        public VehicleViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bind(final VehicleModel vehicle) {
            binding.setVariable(BR.vehicle, vehicle);
            binding.executePendingBindings();
        }
    }
}
