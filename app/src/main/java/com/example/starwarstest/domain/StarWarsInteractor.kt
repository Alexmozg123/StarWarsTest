package com.example.starwarstest.domain

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship
import com.example.starwarstest.domain.model.UIModel
import com.example.starwarstest.domain.repositories.RetrofitRepository
import com.example.starwarstest.domain.repositories.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StarWarsInteractor @Inject constructor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository,
) {

    suspend fun searchPeopleAndStarshipsByName(name: String): List<UIModel> {
        val resultList = mutableListOf<UIModel>()
        if (name.length >= 2) {
            coroutineScope {
                launch(Dispatchers.IO) {
                    resultList.addAll(
                        listOf(retrofitRepository.getPeopleListByName(name) as UIModel))
                    resultList.addAll(
                        listOf(retrofitRepository.getStarshipListByName(name) as UIModel))
                }
            }
            return resultList
        }
        return emptyList()
    }

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

