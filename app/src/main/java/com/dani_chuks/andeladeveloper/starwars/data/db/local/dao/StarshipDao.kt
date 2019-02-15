package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import io.reactivex.Flowable

@Dao
interface StarshipDao {
    @get:Query("SELECT * FROM starship")
    val all: Flowable<List<Starship>>

    @get:Query("SELECT * FROM starship ORDER BY name ASC")
    val allAlphabetically: Flowable<List<Starship>>

    @Query("SELECT * FROM starship LIMIT :size")
    fun getItemBySize(size: Int): Flowable<List<Starship>>

    @Query("SELECT * FROM starship WHERE name = :starshipName")
    fun getPeopleByName(starshipName: String): Flowable<Starship>

    @Query("SELECT * FROM starship WHERE url = :starshipUrl")
    fun getStarshipByURL(starshipUrl: String): Flowable<Starship>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStarshipList(starships: List<Starship>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStarship(starship: Starship)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateS(vehicle: Vehicle)
}
