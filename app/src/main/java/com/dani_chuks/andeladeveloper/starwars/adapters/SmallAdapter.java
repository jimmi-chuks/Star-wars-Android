package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.presentation_models.ItemModel;
import com.dani_chuks.andeladeveloper.presentation_models.PersonModel;
import com.dani_chuks.andeladeveloper.presentation_models.PlanetModel;
import com.dani_chuks.andeladeveloper.presentation_models.SpecieModel;
import com.dani_chuks.andeladeveloper.presentation_models.StarshipModel;
import com.dani_chuks.andeladeveloper.presentation_models.VehicleModel;
import com.dani_chuks.andeladeveloper.starwars.R;

import java.util.List;

public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.BaseViewHolder> {

    List<? extends ItemModel> itemModel;

    public SmallAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        switch (viewType) {
            case 1:
                return new FilmViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_film_item, viewGroup, false));
            case 2:
                return new PersonViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_person_item, viewGroup, false));
            case 3:
                return new PlanetViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_planet_item, viewGroup, false));
            case 4:
                return new SpecieViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_specie_item, viewGroup, false));
            case 5:
                return new StarshipViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_starship_item, viewGroup, false));
            case 6:
                return new VehicleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.small_vehicle_item, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder baseViewHolder, final int position) {
        ItemModel model = itemModel.get(position);
        baseViewHolder.bind(model);
    }

    public void setFilms(List<ItemModel> itemModel) {
        this.itemModel = itemModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return itemModel.get(position).getModelType();
    }

    @Override
    public int getItemCount() {
        return itemModel.size();
    }


    public abstract class BaseViewHolder<T extends ItemModel> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bind(T object);
    }


    public class FilmViewHolder extends BaseViewHolder<FilmModel> {

        TextView title;
        TextView openingCrawl;
        TextView releaseDate;
        Context context;

        public FilmViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            openingCrawl = itemView.findViewById(R.id.opening_crawl);
            releaseDate = itemView.findViewById(R.id.release_date);
            context = itemView.getContext();
        }

        @Override
        public void bind(final FilmModel film) {
            String titleText = context.getString(R.string.card_title, film.getTitle());
            String openingCrawlText = context.getString(R.string.card_opening_crawl, film.getOpeningCrawl());
            String releaseDateText = context.getString(R.string.card_release_date, film.getReleaseDate());
            title.setText(titleText);
            openingCrawl.setText(openingCrawlText);
            releaseDate.setText(releaseDateText);
        }
    }


    public class PersonViewHolder extends BaseViewHolder<PersonModel> {

        TextView name;
        TextView gender;
        TextView birthYear;
        TextView skinColor;
        Context context;

        public PersonViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            birthYear = itemView.findViewById(R.id.birth_year);
            skinColor = itemView.findViewById(R.id.skin_color);
            context = itemView.getContext();
        }

        @Override
        public void bind(final PersonModel person) {
            String nameText = context.getString(R.string.card_name, person.getName());
            String genderText = context.getString(R.string.card_gender, person.getGender());
            String birthYearText = context.getString(R.string.card_birth_year, person.getBirthYear());
            String skinColorText = context.getString(R.string.card_skin_color, person.getSkinColor());
            name.setText(nameText);
            gender.setText(genderText);
            birthYear.setText(birthYearText);
            skinColor.setText(skinColorText);
        }
    }


    public class PlanetViewHolder extends BaseViewHolder<PlanetModel> {

        TextView name;
        TextView diameter;
        TextView gravity;
        TextView terrain;
        Context context;

        public PlanetViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            diameter = itemView.findViewById(R.id.diameter);
            gravity = itemView.findViewById(R.id.gravity);
            terrain = itemView.findViewById(R.id.terrain);
            context = itemView.getContext();
        }

        @Override
        public void bind(final PlanetModel planet) {
            String nameText = context.getString(R.string.card_title, planet.getName());
            String diameterText = context.getString(R.string.card_classification, planet.getDiameter());
            String gravityText = context.getString(R.string.card_language, planet.getGravity());
            String terrainText = context.getString(R.string.card_terrain, planet.getGravity());
            name.setText(nameText);
            diameter.setText(diameterText);
            gravity.setText(gravityText);
            terrain.setText(terrainText);
        }
    }


    public class SpecieViewHolder extends BaseViewHolder<SpecieModel> {

        TextView name;
        TextView classification;
        TextView language;
        TextView designation;
        Context context;

        public SpecieViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            classification = itemView.findViewById(R.id.classification);
            language = itemView.findViewById(R.id.language);
            designation = itemView.findViewById(R.id.designation);
            context = itemView.getContext();
        }

        @Override
        public void bind(final SpecieModel specie) {
            String nameText = context.getString(R.string.card_title, specie.getName());
            String classificationText = context.getString(R.string.card_classification, specie.getClassification());
            String languageText = context.getString(R.string.card_language, specie.getClassification());
            String designationText = context.getString(R.string.card_designation, specie.getDesignation());

            name.setText(nameText);
            classification.setText(classificationText);
            language.setText(languageText);
            designation.setText(designationText);
        }
    }


    public class StarshipViewHolder extends BaseViewHolder<StarshipModel> {

        TextView name;
        TextView crew;
        TextView passenger;
        TextView hyperdriveRating;
        Context context;

        public StarshipViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            crew = itemView.findViewById(R.id.crew);
            passenger = itemView.findViewById(R.id.passenger);
            hyperdriveRating = itemView.findViewById(R.id.hyperdrive_rating);
            context = itemView.getContext();
        }

        @Override
        public void bind(final StarshipModel starship) {
            String nameText = context.getString(R.string.card_title, starship.getName());
            String crewText = context.getString(R.string.card_crew, starship.getCrew());
            String passengerText = context.getString(R.string.card_passenger, starship.getPassengers());
            String hyperdriveRatingText = context.getString(R.string.card_hyper_drive_rating, starship.getName());
            name.setText(nameText);
            crew.setText(crewText);
            passenger.setText(passengerText);
            hyperdriveRating.setText(hyperdriveRatingText);
        }
    }


    public class VehicleViewHolder extends BaseViewHolder<VehicleModel> {

        TextView name;
        TextView crew;
        TextView passenger;
        TextView vehicleClass;
        Context context;

        public VehicleViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            crew = itemView.findViewById(R.id.crew);
            passenger = itemView.findViewById(R.id.passenger);
            vehicleClass = itemView.findViewById(R.id.vehicle_class);
            context = itemView.getContext();
        }

        @Override
        public void bind(final VehicleModel vehicle) {
            String nameText = context.getString(R.string.card_title, vehicle.getName());
            String crewText = context.getString(R.string.card_crew, vehicle.getCrew());
            String passengerText = context.getString(R.string.card_passenger, vehicle.getPassengers());
            String vehicleClassText = context.getString(R.string.card_vehicle_class, vehicle.getVehicleClass());
            name.setText(nameText);
            crew.setText(crewText);
            passenger.setText(passengerText);
            vehicleClass.setText(vehicleClassText);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView openingCrawl;
        TextView releaseDate;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            openingCrawl = itemView.findViewById(R.id.opening_crawl);
            releaseDate = itemView.findViewById(R.id.release_date);
            context = itemView.getContext();
        }
    }
}
