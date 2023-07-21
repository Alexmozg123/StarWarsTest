package com.example.starwarstest.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarstest.data.room.entities.PeopleEntity

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(peopleEntity: PeopleEntity)

    @Query("SELECT * FROM people_entity")
    suspend fun getPeopleList(): List<PeopleEntity>

    @Delete
    suspend fun deletePeople(peopleEntity: PeopleEntity)
}