package com.dani_chuks.andeladeveloper.presentation_models.mappers;

import android.util.Log;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.presentation_models.PersonModel;
import com.dani_chuks.andeladeveloper.presentation_models.PlanetModel;
import com.dani_chuks.andeladeveloper.presentation_models.SpecieModel;
import com.dani_chuks.andeladeveloper.presentation_models.StarshipModel;
import com.dani_chuks.andeladeveloper.presentation_models.VehicleModel;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static <Output, Input> Output mapModel(Type type, Input input) {
        Output tp;
//        Type type = new TypeToken<Output>(){}.getType();
        try {
            String jsonString = gson.toJson(input);
            tp = gson.fromJson(jsonString, type);
            return tp;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", type.getTypeName() + ":  ", exception);
        }
        return null;
    }

    public static <Output, Input> Output mapModel2(Output output, Input input) {
        Output tp;
        Type type = new TypeToken<Output>() {}.getType();
        try {
            String jsonString = gson.toJson(input);
            tp = gson.fromJson(jsonString, type);
            return tp;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", type.getTypeName() + ":  ", exception);
        }
        return null;
    }


    public static <Input> ArrayList<FilmModel> mapFims(List<Input> inputs) {
        ArrayList<FilmModel> outputList;
        Type listType = new TypeToken<ArrayList<FilmModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", FilmModel.class + ":  ", exception);
        }
        return null;
    }

    public static <Input> ArrayList<PersonModel> mapPeople(List<Input> inputs) {
        ArrayList<PersonModel> outputList;
        Type listType = new TypeToken<ArrayList<PersonModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", PersonModel.class + ":  ", exception);
        }
        return null;
    }

    public static <Input> ArrayList<PlanetModel> mapPlanets(List<Input> inputs) {
        ArrayList<PlanetModel> outputList;
        Type listType = new TypeToken<ArrayList<PlanetModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", PlanetModel.class + ":  ", exception);
        }
        return null;
    }

    public static <Input> ArrayList<SpecieModel> mapSpecies(List<Input> inputs) {
        ArrayList<SpecieModel> outputList;
        Type listType = new TypeToken<ArrayList<SpecieModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", SpecieModel.class + ":  ", exception);
        }
        return null;
    }

    public static <Input> ArrayList<StarshipModel> mapStarships(List<Input> inputs) {
        ArrayList<StarshipModel> outputList;
        Type listType = new TypeToken<ArrayList<StarshipModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", StarshipModel.class + ":  ", exception);
        }
        return null;
    }

    public static <Input> ArrayList<VehicleModel> mapVehicles(List<Input> inputs) {
        ArrayList<VehicleModel> outputList;
        Type listType = new TypeToken<ArrayList<VehicleModel>>() {}.getType();
        try {
            String json = gson.toJson(inputs, listType);
            outputList = gson.fromJson(json, listType);
            return outputList;
        } catch (JsonSyntaxException exception) {
            Log.d("TAG", VehicleModel.class + ":  ", exception);
        }
        return null;
    }
}
