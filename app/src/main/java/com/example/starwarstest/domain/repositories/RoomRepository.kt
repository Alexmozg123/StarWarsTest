package com.example.starwarstest.domain.repositories

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship

interface RoomRepository {

    suspend fun doPeopleFavourite(people: People)

    suspend fun doStarshipFavourite(starship: Starship)

    suspend fun doPeopleNotFavourite(people: People)

    suspend fun doStarshipNotFavourite(starship: Starship)

    suspend fun getStarshipList(): List<Starship>

    suspend fun getPeopleList(): List<People>
}