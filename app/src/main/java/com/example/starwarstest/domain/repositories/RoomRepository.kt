package com.example.starwarstest.domain.repositories

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun doPeopleFavourite(people: People)

    suspend fun doStarshipFavourite(starship: Starship)

    suspend fun doPeopleNotFavourite(people: People)

    suspend fun doStarshipNotFavourite(starship: Starship)

    fun getStarshipList(): Flow<List<Starship>>

    fun getPeopleList(): Flow<List<People>>
}