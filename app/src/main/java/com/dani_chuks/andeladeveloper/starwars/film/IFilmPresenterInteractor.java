package com.dani_chuks.andeladeveloper.starwars.film;

import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;

public interface IFilmPresenterInteractor {
    void connectDataSource(DataSource dataSource);
    void disconnectDataSource();
}
