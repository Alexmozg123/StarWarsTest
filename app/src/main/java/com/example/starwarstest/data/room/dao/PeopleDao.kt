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
    fun insertPeople(peopleEntity: PeopleEntity)

    @Query("SELECT * FROM people_entity")
    fun getPeopleList(): List<PeopleEntity>

    @Delete
    fun deletePeople(peopleEntity: PeopleEntity)
}