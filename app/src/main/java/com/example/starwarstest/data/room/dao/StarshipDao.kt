package com.example.starwarstest.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarstest.data.room.entities.StarshipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarshipDao {

    @Query("SELECT * FROM starship_entity")
    fun getStarshipList(): Flow<List<StarshipEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starshipEntity: StarshipEntity)

    @Delete
    suspend fun deleteStarship(starshipEntity: StarshipEntity)
}