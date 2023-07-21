package com.example.starwarstest.data.repository

import com.example.starwarstest.data.api.ApiService
import com.example.starwarstest.data.room.dao.PeopleDao
import com.example.starwarstest.data.room.dao.StarshipDao
import com.example.starwarstest.data.room.entities.PeopleEntity
import com.example.starwarstest.data.room.entities.StarshipEntity
import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship
import com.example.starwarstest.domain.repositories.RetrofitRepository
import com.example.starwarstest.domain.repositories.RoomRepository

class RepositoryImpl(
    private val apiService: ApiService,
    private val peopleDao: PeopleDao,
    private val starshipDao: StarshipDao,
) : RetrofitRepository, RoomRepository {

    override suspend fun getPeopleListByName(name: String): List<People> =
        apiService.getPeopleResponseByName(name).results
            .map { peopleBin ->
                People(
                    peopleBin.name,
                    peopleBin.gender,
                    peopleBin.starships.size
                )
            }

    override suspend fun getStarshipListByName(name: String): List<Starship> =
        apiService.getStarshipResponseByName(name).results
            .map { starshipBin ->
                Starship(
                    starshipBin.name,
                    starshipBin.model,
                    starshipBin.manufacturer,
                    starshipBin.passengers.toInt()
                )
            }

    override suspend fun doPeopleFavourite(people: People) {
        val peopleEntity = PeopleEntity.convertFromPeople(people)
        peopleDao.insertPeople(peopleEntity)
    }

    override suspend fun doStarshipFavourite(starship: Starship) {
        val starshipEntity = StarshipEntity.convertFromStarship(starship)
        starshipDao.insertStarship(starshipEntity)
    }

    override suspend fun doPeopleNotFavourite(people: People) {
        val peopleEntity = PeopleEntity.convertFromPeople(people)
        peopleDao.deletePeople(peopleEntity)
    }

    override suspend fun doStarshipNotFavourite(starship: Starship) {
        val starshipEntity = StarshipEntity.convertFromStarship(starship)
        starshipDao.deleteStarship(starshipEntity)
    }

    override suspend fun getStarshipList(): List<Starship> =
        starshipDao.getStarshipList().map { starshipEntity -> starshipEntity.convertToStarship() }

    override suspend fun getPeopleList(): List<People> =
        peopleDao.getPeopleList().map { peopleEntity -> peopleEntity.convertToPeople() }
}