package com.example.starwarstest.domain

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship
import com.example.starwarstest.domain.repositories.RetrofitRepository
import com.example.starwarstest.domain.repositories.RoomRepository

class StarWarsInteractor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository,
) {

    suspend fun getPeopleListByName(name: String): List<People> =
        retrofitRepository.getPeopleListByName(name)

    suspend fun getStarshipListByName(name: String): List<Starship> =
        retrofitRepository.getStarshipListByName(name)

    suspend fun doFavourite(entity: Any) {
        when (entity) {
            is People -> roomRepository.doPeopleFavourite(entity)
            is Starship -> roomRepository.doStarshipFavourite(entity)
        }
    }

    suspend fun doNotFavourite(entity: Any) {
        when (entity) {
            is People -> roomRepository.doPeopleNotFavourite(entity)
            is Starship -> roomRepository.doStarshipNotFavourite(entity)
        }
    }

    suspend fun readFavouritePeople(): List<People> = roomRepository.getPeopleList()

    suspend fun readFavouriteStarship(): List<Starship> = roomRepository.getStarshipList()
}