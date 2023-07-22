package com.example.starwarstest.domain.repositories

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship

interface RoomRepository {

    fun doPeopleFavourite(people: People)

    fun doStarshipFavourite(starship: Starship)

    fun doPeopleNotFavourite(people: People)

    fun doStarshipNotFavourite(starship: Starship)

    fun getStarshipList(): List<Starship>

    fun getPeopleList(): List<People>
}