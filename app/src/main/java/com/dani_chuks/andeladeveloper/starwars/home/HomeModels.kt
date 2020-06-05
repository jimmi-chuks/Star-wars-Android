package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.MainModels
import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*
import com.dani_chuks.andeladeveloper.starwars.base.mvi.Intent
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film

//val getUserChanges = getUsersUseCase()
//        .onEach { Log.d("###", "[MAIN_VM] Emit users.size=${it.size}") }
//        .map {
//            val items = it.map(::UserItem)
//            PartialChange.GetUser.Data(items) as PartialChange.GetUser
//        }
//        .onStart { emit(PartialChange.GetUser.Loading) }
//        .catch { emit(PartialChange.GetUser.Error(it)) }
//
//val refreshChanges = flow { emit(refreshGetUsersUseCase()) }
//        .map { PartialChange.Refresh.Success as PartialChange.Refresh }
//        .onStart { emit(PartialChange.Refresh.Loading) }
//        .catch { emit(PartialChange.Refresh.Failure(it)) }
//
//return merge(
//filterIsInstance<ViewIntent.Initial>()
//.logIntent()
//.flatMapConcat { getUserChanges },
//filterIsInstance<ViewIntent.Refresh>()
//.filter { _viewStateD.value?.let { !it.isLoading && it.error === null } ?: false }
//.logIntent()
//.flatMapFirst { refreshChanges },
//filterIsInstance<ViewIntent.Retry>()
//.filter { _viewStateD.value?.error != null }
//.logIntent()
//.flatMapFirst { getUserChanges },
//filterIsInstance<ViewIntent.RemoveUser>()
//.logIntent()
//.map { it.user }
//.flatMapMerge { userItem ->
//    flow {
//        try {
//            removeUserUseCase(userItem.toDomain())
//            emit(PartialChange.RemoveUser.Success(userItem))
//        } catch (e: Exception) {
//            emit(PartialChange.RemoveUser.Failure(userItem, e))
//        }
//    }
//}
//)

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
)

sealed class HomeViewAction {
    data class ShowAllAction(val itemModelType: ItemModelType) : HomeViewAction()

    data class ShowItemAction(val type: ItemModelType, val itemURL: String) : HomeViewAction()
}


sealed class HomeEvent {
    object InitEvents : HomeEvent()

    data class FetchAllEvent(val type: ItemModelType) : HomeEvent()

    data class ShowAllEvent(val itemModelType: ItemModelType) : HomeEvent()

    data class ShowItemEvent(val itemURL: String, val itemModelType: ItemModelType) : HomeEvent()
}

sealed class HomeIntents : Intent<HomeState> {
    object Init : HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

    data class ObserveItems(val itemModelType: ItemModelType) : HomeIntents() {
        override fun reduce(oldState: HomeState) =
                when (itemModelType) {
                    ItemModelType.FILM -> oldState.copy(moviesLoading = true)
                    ItemModelType.PERSON -> oldState.copy(peopleLoading = true)
                    ItemModelType.PLANET -> oldState.copy(planetsLoading = true)
                    ItemModelType.SPECIE -> oldState.copy(speciesLoading = true)
                    ItemModelType.STARSHIP -> oldState.copy(starshipsLoading = true)
                    ItemModelType.VEHICLE -> oldState.copy(vehiclesLoading = true)
                }
    }

    data class ShowItemIntent(val itemURL: String, val itemModelType: ItemModelType) : HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

    data class ShowAllIntent(val itemModelType: ItemModelType) : HomeIntents() {
        override fun reduce(oldState: HomeState) = oldState
    }

}