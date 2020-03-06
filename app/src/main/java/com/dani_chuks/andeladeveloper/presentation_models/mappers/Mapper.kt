package com.dani_chuks.andeladeveloper.presentation_models.mappers

import android.util.Log
import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object Mapper {

//    TODO: Use Reified to refactor line
//     using https://proandroiddev.com/how-reified-type-makes-kotlin-so-much-better-7ae539ed0304

    var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    fun <Output, Input> mapModel(type: Type, input: Input): Output? {
        val tp: Output
        //        Type type = new TypeToken<Output>(){}.getType();
        try {
            val jsonString = gson.toJson(input)
            tp = gson.fromJson(jsonString, type)
            return tp
        } catch (exception: Throwable) {
            Log.d("TAG", "$type:  ", exception)
        }

        return null
    }

    fun <Output, Input> mapModel2(output: Output, input: Input): Output? {
        val tp: Output
        val type = object : TypeToken<Output>() {

        }.type
        try {
            val jsonString = gson.toJson(input)
            tp = gson.fromJson(jsonString, type)
            return tp
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", type.toString() + ":  ", exception)
        }

        return null
    }


    fun <Input> mapFims(inputs: List<Input>): ArrayList<FilmModel>? {
        val outputList: ArrayList<FilmModel>
        val listType = object : TypeToken<ArrayList<FilmModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", FilmModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }

    fun <Input> mapPeople(inputs: List<Input>): ArrayList<PersonModel>? {
        val outputList: ArrayList<PersonModel>
        val listType = object : TypeToken<ArrayList<PersonModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", PersonModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }

    fun <Input> mapPlanets(inputs: List<Input>): ArrayList<PlanetModel>? {
        val outputList: ArrayList<PlanetModel>
        val listType = object : TypeToken<ArrayList<PlanetModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", PlanetModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }

    fun <Input> mapSpecies(inputs: List<Input>): ArrayList<SpecieModel>? {
        val outputList: ArrayList<SpecieModel>
        val listType = object : TypeToken<ArrayList<SpecieModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", SpecieModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }

    fun <Input> mapStarships(inputs: List<Input>): ArrayList<StarshipModel>? {
        val outputList: ArrayList<StarshipModel>
        val listType = object : TypeToken<ArrayList<StarshipModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", StarshipModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }

    fun <Input> mapVehicles(inputs: List<Input>): ArrayList<VehicleModel>? {
        val outputList: ArrayList<VehicleModel>
        val listType = object : TypeToken<ArrayList<VehicleModel>>() {

        }.type
        try {
            val json = gson.toJson(inputs, listType)
            outputList = gson.fromJson(json, listType)
            return outputList
        } catch (exception: JsonSyntaxException) {
            Log.d("TAG", VehicleModel::class.java.simpleName + ":  ", exception)
        }

        return null
    }
}
