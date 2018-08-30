package com.dani_chuks.andeladeveloper.starwars.home;

import io.reactivex.disposables.Disposable;

public interface IHomePresenter {
    void initView(HomeView view);
    void fetchData();
    void addDisposable(Disposable disposable);
    void clear();
}
