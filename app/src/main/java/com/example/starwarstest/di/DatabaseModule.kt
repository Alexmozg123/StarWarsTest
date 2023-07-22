package com.example.starwarstest.di

import android.content.Context
import androidx.room.Room
import com.example.starwarstest.data.room.FavouriteDatabase
import com.example.starwarstest.data.room.dao.PeopleDao
import com.example.starwarstest.data.room.dao.StarshipDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideFavouriteDatabase(
        context: Context
    ): FavouriteDatabase =
        Room.databaseBuilder(context, FavouriteDatabase::class.java, "starWarsDB")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideStarshipDao(
        favouriteDatabase: FavouriteDatabase
    ): StarshipDao = favouriteDatabase.getStarshipDao()

    @Provides
    fun providePeopleDao(
        favouriteDatabase: FavouriteDatabase
    ): PeopleDao = favouriteDatabase.getPeopleDao()
}