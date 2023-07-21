package com.example.starwarstest.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarstest.data.room.entities.StarshipEntity

@Dao
interface StarshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starshipEntity: StarshipEntity)

    @Query("SELECT * FROM starship_entity")
    suspend fun getStarshipList(): List<StarshipEntity>

    @Delete
    suspend fun deleteStarship(starshipEntity: StarshipEntity)
}