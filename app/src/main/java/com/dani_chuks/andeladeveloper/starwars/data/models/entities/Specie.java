
package com.dani_chuks.andeladeveloper.starwars.data.models.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Specie {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    @NonNull
    private String url;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "classification")
    @SerializedName("classification")
    @Expose
    private String classification;
    @ColumnInfo(name = "designation")
    @SerializedName("designation")
    @Expose
    private String designation;
    @ColumnInfo(name = "average_height")
    @SerializedName("average_height")
    @Expose
    private String averageHeight;
    @ColumnInfo(name = "skin_colors")
    @SerializedName("skin_colors")
    @Expose
    private String skinColors;
    @ColumnInfo(name = "hair_colors")
    @SerializedName("hair_colors")
    @Expose
    private String hairColors;
    @ColumnInfo(name = "eye_colors")
    @SerializedName("eye_colors")
    @Expose
    private String eyeColors;
    @ColumnInfo(name = "average_lifespan")
    @SerializedName("average_lifespan")
    @Expose
    private String averageLifespan;
    @ColumnInfo(name = "home_world")
    @SerializedName("home_world")
    @Expose
    private String homeWorld;
    @ColumnInfo(name = "language")
    @SerializedName("language")
    @Expose
    private String language;
    @ColumnInfo(name = "people")
    @SerializedName("people")
    @Expose
    private List<String> people = null;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(String skinColors) {
        this.skinColors = skinColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(String hairColors) {
        this.hairColors = hairColors;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(String eyeColors) {
        this.eyeColors = eyeColors;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(String averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
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
