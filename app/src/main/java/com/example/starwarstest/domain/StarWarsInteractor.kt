package com.example.starwarstest.domain

import com.example.starwarstest.domain.model.UIModel
import com.example.starwarstest.domain.repositories.RetrofitRepository
import com.example.starwarstest.domain.repositories.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class StarWarsInteractor @Inject constructor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository,
) {

    suspend fun searchPeopleAndStarshipsByName(name: String): List<UIModel> {
        val resultList = mutableListOf<UIModel>()
        if (name.length >= 2) {
            resultList.addAll(
                retrofitRepository.getStarshipListByName(name).map { UIModel.StarshipModel(it) }
            )
            resultList.addAll(
                retrofitRepository.getPeopleListByName(name).map { UIModel.PeopleModel(it) }
            )
            return resultList
        }
        return emptyList()
    }

    suspend fun doFavourite(entity: UIModel) {
        when (entity) {
            is UIModel.PeopleModel -> roomRepository.doPeopleFavourite(entity.people)
            is UIModel.StarshipModel -> roomRepository.doStarshipFavourite(entity.starship)
        }
    }

    suspend fun doNotFavourite(entity: UIModel) {
        when (entity) {
            is UIModel.PeopleModel -> roomRepository.doPeopleNotFavourite(entity.people)
            is UIModel.StarshipModel -> roomRepository.doStarshipNotFavourite(entity.starship)
        }
    }

    fun readFavourite(): Flow<List<UIModel>> {
        return flow {
            val starshipFlow = roomRepository.getStarshipList()
                .map { starshipList -> starshipList.map { UIModel.StarshipModel(it) } }
            val peopleFlow = roomRepository.getPeopleList()
                .map { peopleList -> peopleList.map { UIModel.PeopleModel(it) } }

            combine(starshipFlow, peopleFlow) { starships, people ->
                emit( people + starships)
            }.collect()
        }
    }
}

