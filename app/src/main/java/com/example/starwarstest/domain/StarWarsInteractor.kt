package com.example.starwarstest.domain

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
                    resultList.addAll(retrofitRepository.getStarshipListByName(name)
                        .map { UIModel.StarshipModel(it) })
                    resultList.addAll(retrofitRepository.getPeopleListByName(name)
                        .map { UIModel.PeopleModel(it) })
                }
            }
            return resultList
        }
        return emptyList()
    }

    fun doFavourite(entity: UIModel) {
        when (entity) {
            is UIModel.PeopleModel -> roomRepository.doPeopleFavourite(entity.people)
            is UIModel.StarshipModel -> roomRepository.doStarshipFavourite(entity.starship)
        }
    }

    fun doNotFavourite(entity: UIModel) {
        when (entity) {
            is UIModel.PeopleModel -> roomRepository.doPeopleNotFavourite(entity.people)
            is UIModel.StarshipModel -> roomRepository.doStarshipNotFavourite(entity.starship)
        }
    }

    suspend fun readFavourite(): List<UIModel> {
        val resultList = mutableListOf<UIModel>()
        coroutineScope {
            launch(Dispatchers.IO) {
                resultList.addAll(roomRepository.getPeopleList()
                    .map { UIModel.PeopleModel(it) })
                resultList.addAll(roomRepository.getStarshipList()
                    .map { UIModel.StarshipModel(it) })
            }
        }
        return resultList
    }
}

