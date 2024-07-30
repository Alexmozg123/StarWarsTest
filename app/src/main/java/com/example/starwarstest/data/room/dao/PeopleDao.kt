package com.example.starwarstest.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarstest.data.room.entities.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people_entity")
    fun getPeopleList(): Flow<List<PeopleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(peopleEntity: PeopleEntity)

    @Delete
    suspend fun deletePeople(peopleEntity: PeopleEntity)
}