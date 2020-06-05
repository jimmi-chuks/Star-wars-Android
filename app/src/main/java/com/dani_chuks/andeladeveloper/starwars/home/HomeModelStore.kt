package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.mvi.*
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeModelStore @Inject constructor(
        private val interactor: HomeInteractor,
        override val iDispatcherProvider: IDispatcherProvider
): ModelStore<HomeState, HomeViewAction>() {

    override val startingState: HomeState = HomeState()

    override val scope: CoroutineScope = CoroutineScope( SupervisorJob() + iDispatcherProvider.io)

    override fun Flow<Intent<HomeState>>.intentProcessorFlow(): Flow<StoreResult<HomeState, HomeViewAction>> {
        return emptyFlow()
    }

    val remoteItemFetchProcessor: () -> Flow<StoreResult<HomeState, HomeViewAction>> = {
        fetchItemsRemote()
        flow {
            StoreResult<HomeState, HomeViewAction>(noChange<HomeState>())
        }
    }

    val showAllByTypeProcessor: (itemModelType: ItemModelType) -> Flow<StoreResult<HomeState, HomeViewAction>> = { type ->
        flow {
            StoreResult<HomeState, HomeViewAction>(
                    noChange<HomeState>(), HomeViewAction.ShowAllAction(type)
            )
        }
    }

    val showItemProcessor: (itemURL: String, itemModelType: ItemModelType) -> Flow<StoreResult<HomeState, HomeViewAction>> = { url, type ->
        flow {
            StoreResult<HomeState, HomeViewAction>(
                    noChange<HomeState>(), HomeViewAction.ShowItemAction(type, url)
            )
        }
    }

    val observeItemProcessor: (itemModelType: ItemModelType) -> Flow<StoreResult<HomeState, HomeViewAction>> = { type ->
        val flow = when(type){
            ItemModelType.FILM -> movieFlow
            ItemModelType.PERSON -> peopleFlow
            ItemModelType.PLANET -> planetsFlow
            ItemModelType.SPECIE -> speciesFlow
            ItemModelType.STARSHIP -> starshipsFlow
            ItemModelType.VEHICLE -> vehicleFlow
        }
        flow.onStart { emit(StoreResult(HomeIntents.ObserveItems(type))) }
    }

    private val movieFlow = interactor.loadFilms()
            .filterNotNull()
            .map { Mapper.mapFilms(it) }
            .filterNotNull()
            .map { StoreResult<HomeState, HomeViewAction>(intent { copy(movies = it) }) }

    private val peopleFlow = interactor.loadPeople()
            .filterNotNull()
            .map { Mapper.mapPeople(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(people = it) } )
            }

    private val planetsFlow = interactor.loadPlanets()
            .filterNotNull()
            .map { Mapper.mapPlanets(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(planets = it) } )
            }

    private val speciesFlow = interactor.loadPeople()
            .filterNotNull()
            .map { Mapper.mapSpecies(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(species = it) } )
            }

    private val vehicleFlow = interactor.loadVehicles()
            .filterNotNull()
            .map { Mapper.mapVehicles(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(vehicles = it) } )
            }

    private val starshipsFlow = interactor.loadStarships()
            .filterNotNull()
            .map { Mapper.mapStarships(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(starships = it) } )
            }

    fun fetchItemsRemote(){
        scope.launch(iDispatcherProvider.io) { interactor.loadFilmsRemote() }
        scope.launch(iDispatcherProvider.io) { interactor.loadPeopleRemote(firstPage) }
        scope.launch(iDispatcherProvider.io) { interactor.loadPlanetRemote(firstPage) }
        scope.launch(iDispatcherProvider.io) { interactor.loadSpeciesRemote(firstPage) }
        scope.launch(iDispatcherProvider.io) { interactor.loadVehicleRemote(firstPage) }
        scope.launch(iDispatcherProvider.io) { interactor.loadStarshipsRemote(firstPage) }
    }

    companion object {
        const val firstPage = 1
    }
}