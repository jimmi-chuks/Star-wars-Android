package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*
import com.dani_chuks.andeladeveloper.starwars.base.mvi.Intent
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIAction
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIEvent
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIState
import java.lang.Exception

data class HomeState(
        val initialLoading: Boolean = true,
        val moviesLoading: Boolean = true,
        val peopleLoading: Boolean = true,
        val speciesLoading: Boolean = true,
        val planetsLoading: Boolean = true,
        val starshipsLoading: Boolean = true,
        val vehiclesLoading: Boolean = true,
        val movies: List<FilmModel> = emptyList(),
        val people: List<PersonModel> = emptyList(),
        val species: List<SpecieModel> = emptyList(),
        val planets: List<PlanetModel> = emptyList(),
        val vehicles: List<VehicleModel> = emptyList(),
        val starships: List<StarshipModel> = emptyList()
) : MVIState()

sealed class HomeViewAction : MVIAction()  {
    data class ShowAllAction(val itemModelType: ItemModelType) : HomeViewAction()

    data class ShowItemAction(val type: ItemModelType, val itemURL: String) : HomeViewAction()

    data class ShowRemoteFetchError(val itemModelType: ItemModelType, val fetchException: Exception) : HomeViewAction()
}


sealed class HomeEvent : MVIEvent() {
    object InitEvents : HomeEvent()

    data class ShowAllEvent(val itemModelType: ItemModelType) : HomeEvent()

    data class ShowItemEvent(val itemURL: String, val itemModelType: ItemModelType) : HomeEvent()
}

sealed class HomeIntents : Intent<HomeState> {
    object Init : HomeIntents() {
        override fun reduce(oldState: HomeState) =
                oldState.copy(
                        moviesLoading = true,
                        peopleLoading = true,
                        planetsLoading = true,
                        speciesLoading = true,
                        starshipsLoading = true,
                        vehiclesLoading = true
                )
    }

    data class ShowItemIntent(val itemURL: String, val itemModelType: ItemModelType) : HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

    data class ShowAllIntent(val itemModelType: ItemModelType) : HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

    data class RemoteUpdateError(val itemModelType: ItemModelType, val fetchException: Exception): HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

}