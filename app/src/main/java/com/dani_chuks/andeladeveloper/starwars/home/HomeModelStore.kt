package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.mvi.*
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.di.Result
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
        val flows = listOf(
                filterIsInstance<HomeIntents.ShowItemIntent>()
                        .flatMapConcat { showItemProcessor(it.itemURL, it.itemModelType) },
                filterIsInstance<HomeIntents.ShowAllIntent>()
                        .map {
                            StoreResult<HomeState, HomeViewAction>(
                                    noChange<HomeState>(), HomeViewAction.ShowAllAction(it.itemModelType)
                            )
                        },
                filterIsInstance<HomeIntents.Init>()
                        .flatMapMerge { initIntentProcessor },
                filterIsInstance<HomeIntents.RemoteUpdateError>()
                        .map{ StoreResult<HomeState, HomeViewAction>(it)}
        )
        return flows.asFlow().flattenMerge(flows.size)

    }

    val initIntentProcessor =
            channelFlow {
                launch {
                    remoteFetchProcessor().collect { send(it) }
                }
                launch {
                   observeAll().collect{ send(it) }
                }
            }

    fun remoteFetchProcessor() =
            channelFlow<StoreResult<HomeState, HomeViewAction>> {
                launch(iDispatcherProvider.io) {
                    interactor.loadFilmsRemote()
                    send(StoreResult(noChange<HomeState>()))
                }
                launch {
                    interactor.loadPeopleRemote(firstPage)
                    send(StoreResult(noChange<HomeState>()))
                }
                launch(iDispatcherProvider.io) {
                    interactor.loadPeopleRemote(firstPage)
                    send(StoreResult(noChange<HomeState>()))
                }
                launch(iDispatcherProvider.io) {
                    val res = interactor.loadPlanetRemote(firstPage)
                    when (res) {
                        is Result.Success -> {
                            send(StoreResult(noChange<HomeState>()))
                        }
                        is Result.Error -> {
                            send(
                                    StoreResult(
                                            noChange<HomeState>(),
                                            HomeViewAction.ShowRemoteFetchError(ItemModelType.PLANET, res.exception)
                                    )
                            )
                        }
                    }
                }
                launch(iDispatcherProvider.io) {
                    interactor.loadSpeciesRemote(firstPage)
                    send(StoreResult(noChange<HomeState>()))
                }
                launch(iDispatcherProvider.io) {
                    interactor.loadVehicleRemote(firstPage)
                    send(StoreResult(noChange<HomeState>()))
                }
                launch(iDispatcherProvider.io) {
                    interactor.loadStarshipsRemote(firstPage)
                    send(StoreResult(noChange<HomeState>()))
                }
            }

    val observeAll: () -> Flow<StoreResult<HomeState, HomeViewAction>> = {
        val flows =
                listOf(
                        movieFlow, peopleFlow, planetsFlow, speciesFlow, starshipsFlow, vehicleFlow
                )
        flows.asFlow().flattenMerge(flows.size)
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

    private val movieFlow = interactor.loadFilms()
            .filterNotNull()
            .map { Mapper.mapFilms(it) }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map { StoreResult<HomeState, HomeViewAction>(intent { copy( movies = it, moviesLoading = false) }) }

    private val peopleFlow = interactor.loadPeople()
            .filterNotNull()
            .map { Mapper.mapPeople(it) }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(people = it, peopleLoading = false) } )
            }

    private val planetsFlow = interactor.loadPlanets()
            .filterNotNull()
            .map { Mapper.mapPlanets(it) }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(planets = it, planetsLoading = false) } )
            }

    private val speciesFlow = interactor.loadPeople()
            .filterNotNull()
            .map { Mapper.mapSpecies(it) }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(species = it, speciesLoading = false) } )
            }

    private val vehicleFlow = interactor.loadVehicles()
            .filterNotNull()
            .map { Mapper.mapVehicles(it) }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(vehicles = it, vehiclesLoading = false) } )
            }

    private val starshipsFlow = interactor.loadStarships()
            .filterNotNull()
            .map { Mapper.mapStarships(it) }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>( intent { copy(starships = it, starshipsLoading = false ) } )
            }

    companion object {
        const val firstPage = 1
    }
}