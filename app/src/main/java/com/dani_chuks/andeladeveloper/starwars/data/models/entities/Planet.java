
package com.dani_chuks.andeladeveloper.starwars.data.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Planet {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    @NonNull
    private String url;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "rotation_period")
    @SerializedName("rotation_period")
    @Expose
    private String rotationPeriod;
    @ColumnInfo(name = "orbital_period")
    @SerializedName("orbital_period")
    @Expose
    private String orbitalPeriod;
    @ColumnInfo(name = "diameter")
    @SerializedName("diameter")
    @Expose
    private String diameter;
    @ColumnInfo(name = "climate")
    @SerializedName("climate")
    @Expose
    private String climate;
    @ColumnInfo(name = "gravity")
    @SerializedName("gravity")
    @Expose
    private String gravity;
    @ColumnInfo(name = "terrain")
    @SerializedName("terrain")
    @Expose
    private String terrain;
    @ColumnInfo(name = "surface_water")
    @SerializedName("surface_water")
    @Expose
    private String surfaceWater;
    @ColumnInfo(name = "population")
    @SerializedName("population")
    @Expose
    private String population;
    @ColumnInfo(name = "residents")
    @SerializedName("residents")
    @Expose
    private List<String> residents = null;
    @ColumnInfo(name = "films")
    @SerializedName("films")
    @Expose
    private List<String> films = null;
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

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
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
