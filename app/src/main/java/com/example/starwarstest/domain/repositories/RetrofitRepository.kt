package com.example.starwarstest.domain.repositories

import com.example.starwarstest.domain.model.People
import com.example.starwarstest.domain.model.Starship

interface RetrofitRepository {

    suspend fun getPeopleListByName(name: String) : List<People>

    suspend fun getStarshipListByName(name: String) : List<Starship>
}