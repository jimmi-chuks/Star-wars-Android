
package com.dani_chuks.andeladeveloper.starwars.data.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Person {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    @NonNull
    private String url;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "height")
    @SerializedName("height")
    @Expose
    private String height;
    @ColumnInfo(name = "mass")
    @SerializedName("mass")
    @Expose
    private String mass;
    @ColumnInfo(name = "hair_color")
    @SerializedName("hair_color")
    @Expose
    private String hairColor;
    @ColumnInfo(name = "skin_color")
    @SerializedName("skin_color")
    @Expose
    private String skinColor;
    @ColumnInfo(name = "eye_color")
    @SerializedName("eye_color")
    @Expose
    private String eyeColor;
    @ColumnInfo(name = "birth_year")
    @SerializedName("birth_year")
    @Expose
    private String birthYear;
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    private String gender;
    @ColumnInfo(name = "home_world")
    @SerializedName("home_world")
    @Expose
    private String homeWorld;
    @ColumnInfo(name = "films")
    @SerializedName("films")
    @Expose
    private List<String> films = null;
    @ColumnInfo(name = "species")
    @SerializedName("species")
    @Expose
    private List<String> species = null;
    @ColumnInfo(name = "vehicles")
    @SerializedName("vehicles")
    @Expose
    private List<String> vehicles = null;
    @ColumnInfo(name = "starships")
    @SerializedName("starships")
    @Expose
    private List<String> starships = null;
    @ColumnInfo(name = "created")
    @SerializedName("created")
    @Expose
    private String created;
    @ColumnInfo(name = "edited")
    @SerializedName("edited")
    @Expose
    private String edited;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
