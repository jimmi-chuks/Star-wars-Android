
package com.dani_chuks.andeladeveloper.starwars.data.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Starship {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    @NonNull
    private String url;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("model")
    @Expose
    private String model;
    @ColumnInfo(name = "manufacturer")
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @ColumnInfo(name = "cost_in_credits")
    @SerializedName("cost_in_credits")
    @Expose
    private String costInCredits;
    @ColumnInfo(name = "length")
    @SerializedName("length")
    @Expose
    private String length;
    @ColumnInfo(name = "max_atmosphering_speed")
    @SerializedName("max_atmosphering_speed")
    @Expose
    private String maxAtmospheringSpeed;
    @ColumnInfo(name = "crew")
    @SerializedName("crew")
    @Expose
    private String crew;
    @ColumnInfo(name = "passengers")
    @SerializedName("passengers")
    @Expose
    private String passengers;
    @ColumnInfo(name = "cargo_capacity")
    @SerializedName("cargo_capacity")
    @Expose
    private String cargoCapacity;
    @ColumnInfo(name = "consumables")
    @SerializedName("consumables")
    @Expose
    private String consumables;
    @ColumnInfo(name = "hyperdrive_rating")
    @SerializedName("hyperdrive_rating")
    @Expose
    private String hyperdriveRating;
    @ColumnInfo(name = "mglt")
    @SerializedName("MGLT")
    @Expose
    private String mGLT;
    @ColumnInfo(name = "starship_class")
    @SerializedName("starship_class")
    @Expose
    private String starshipClass;
    @ColumnInfo(name = "pilots")
    @SerializedName("pilots")
    @Expose
    private List<String> pilots = null;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public String getMGLT() {
        return mGLT;
    }

    public void setMGLT(String mGLT) {
        this.mGLT = mGLT;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    public List<String> getPilots() {
        return pilots;
    }

    public void setPilots(List<String> pilots) {
        this.pilots = pilots;
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
