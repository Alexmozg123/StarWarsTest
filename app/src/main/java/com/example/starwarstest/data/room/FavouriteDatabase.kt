package com.example.starwarstest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarstest.data.room.dao.PeopleDao
import com.example.starwarstest.data.room.dao.StarshipDao
import com.example.starwarstest.data.room.entities.PeopleEntity
import com.example.starwarstest.data.room.entities.StarshipEntity

@Database(
    entities = [PeopleEntity::class, StarshipEntity::class],
    version = 1
)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun getPeopleDao(): PeopleDao
    abstract fun getStarshipDao(): StarshipDao
}