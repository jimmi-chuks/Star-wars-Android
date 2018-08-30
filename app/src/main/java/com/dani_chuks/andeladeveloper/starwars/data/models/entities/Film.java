
package com.dani_chuks.andeladeveloper.starwars.data.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Film {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    @NonNull
    private String url;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name = "episode_id")
    @SerializedName("episode_id")
    @Expose
    private Integer episodeId;
    @ColumnInfo(name = "opening_crawl")
    @SerializedName("opening_crawl")
    @Expose
    private String openingCrawl;
    @ColumnInfo(name = "director")
    @SerializedName("director")
    @Expose
    private String director;
    @ColumnInfo(name = "producer")
    @SerializedName("producer")
    @Expose
    private String producer;
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @ColumnInfo(name = "characters")
    @SerializedName("characters")
    @Expose
    private List<String> characters = null;
    @ColumnInfo(name = "planets")
    @SerializedName("planets")
    @Expose
    private List<String> planets = null;
    @ColumnInfo(name = "starships")
    @SerializedName("starships")
    @Expose
    private List<String> starships = null;
    @ColumnInfo(name = "vehicles")
    @SerializedName("vehicles")
    @Expose
    private List<String> vehicles = null;
    @ColumnInfo(name = "species")
    @SerializedName("species")
    @Expose
    private List<String> species = null;
    @ColumnInfo(name = "created")
    @SerializedName("created")
    @Expose
    private String created;
    @ColumnInfo(name = "edited")
    @SerializedName("edited")
    @Expose
    private String edited;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
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
