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
    fun insertStarship(starshipEntity: StarshipEntity)

    @Query("SELECT * FROM starship_entity")
    fun getStarshipList(): List<StarshipEntity>

    @Delete
    fun deleteStarship(starshipEntity: StarshipEntity)
}