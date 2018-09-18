package com.dani_chuks.andeladeveloper.presentation_models.mappers;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;

public class Mapper {

    public static FilmModel mapFilmModel(Film film){
        FilmModel filmModel = new FilmModel(film.getUrl());
        filmModel.setTitle(film.getTitle());
        filmModel.setCharacters(film.getCharacters());
        filmModel.setCreated(film.getCreated());
        filmModel.setDirector(film.getDirector());
        filmModel.setEdited(film.getEdited());
        filmModel.setEpisodeId(film.getEpisodeId());

        return filmModel;
    }
}
