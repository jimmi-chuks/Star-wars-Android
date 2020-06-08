package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.mvi.*
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeModelStore @Inject constructor(
        private val interactor: HomeInteractor,
        override val iDispatcherProvider: IDispatcherProvider
) : ModelStore<HomeState, HomeViewAction>() {

    override val startingState: HomeState = HomeState()

    override fun Flow<Intent<HomeState>>.intentProcessorFlow(): Flow<StoreResult<HomeState, HomeViewAction>> {
        val flows = listOf(
                filterIsInstance<HomeIntents.ShowItemIntent>()
                        .map {
                            storeAction<HomeState, HomeViewAction> { HomeViewAction.ShowItemAction(it.itemModelType, it.itemURL) }
                        },
                filterIsInstance<HomeIntents.ShowAllIntent>()
                        .map {
                            storeAction<HomeState, HomeViewAction> { HomeViewAction.ShowAllAction(it.itemModelType) }
                        },
                filterIsInstance<HomeIntents.Init>()
                        .flatMapMerge { initIntentProcessor },
                filterIsInstance<HomeIntents.RemoteUpdateError>()
                        .map { StoreResult<HomeState, HomeViewAction>(it) }
        )
        return flows.asFlow().flattenMerge(flows.size)

    }

    private val initIntentProcessor =
            channelFlow {
                launch { remoteFetchProcessor().collect { send(it) } }
                launch { observeAll().collect { send(it) } }
            }

    private val remoteFetchProcessor: () -> Flow<StoreResult<HomeState, HomeViewAction>> = {
        channelFlow<StoreResult<HomeState, HomeViewAction>> {
            launch(iDispatcherProvider.io) { interactor.loadFilmsRemote() }
            launch { interactor.loadPeopleRemote(firstPage) }
            launch(iDispatcherProvider.io) { interactor.loadPeopleRemote(firstPage) }
            launch(iDispatcherProvider.io) {
                val result = interactor.loadPlanetRemote(firstPage)
                if(result is Result.Error){
                    send(
                            storeAction<HomeState, HomeViewAction> {
                                HomeViewAction.ShowRemoteFetchError(ItemModelType.PLANET, result.exception)
                            }
                    )
                }
            }

            launch(iDispatcherProvider.io) { interactor.loadSpeciesRemote(firstPage) }

            launch(iDispatcherProvider.io) { interactor.loadVehicleRemote(firstPage) }

            launch(iDispatcherProvider.io) { interactor.loadStarshipsRemote(firstPage) }
        }
    }

    val observeAll: () -> Flow<StoreResult<HomeState, HomeViewAction>> = {
        val flows =
                listOf(
                        movieFlow, peopleFlow, planetsFlow, speciesFlow, starshipsFlow, vehicleFlow
                )
        flows.asFlow().flattenMerge(flows.size)
    }

    private val movieFlow = interactor.loadFilms()
            .map { it?.let { Mapper.mapFilms(it) } }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map { StoreResult<HomeState, HomeViewAction>(intent { copy(movies = it, moviesLoading = false) }) }

    private val peopleFlow = interactor.loadPeople()
            .map { it?.let { Mapper.mapPeople(it) } }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>(intent { copy(people = it, peopleLoading = false) })
            }

    private val planetsFlow = interactor.loadPlanets()
            .map { it?.let { Mapper.mapPlanets(it) } }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>(intent { copy(planets = it, planetsLoading = false) })
            }

    private val speciesFlow = interactor.loadPeople()
            .map { it?.let { Mapper.mapSpecies(it) } }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>(intent { copy(species = it, speciesLoading = false) })
            }

    private val vehicleFlow = interactor.loadVehicles()
            .map { it?.let { Mapper.mapVehicles(it) } }
            .filterNotNull()
            .flowOn(iDispatcherProvider.io)
            .map {
                StoreResult<HomeState, HomeViewAction>(intent { copy(vehicles = it, vehiclesLoading = false) })
            }

    private val starshipsFlow = interactor.loadStarships()
            .map { it?.let { Mapper.mapStarships(it) } }
            .filterNotNull()
            .map {
                StoreResult<HomeState, HomeViewAction>(intent { copy(starships = it, starshipsLoading = false) })
            }

    companion object {
        const val firstPage = 1
    }
}