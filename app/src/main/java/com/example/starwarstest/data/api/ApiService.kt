package com.example.starwarstest.data.api

import com.example.starwarstest.data.repository.model.PeopleResponse
import com.example.starwarstest.data.repository.model.StarshipResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("people/?search={name}")
    suspend fun getPeopleResponseByName(
        @Query("search") peopleName: String
    ): PeopleResponse

    @GET("starship/?search={name}")
    suspend fun getStarshipResponseByName(
        @Query("search") starshipName: String
    ): StarshipResponse
}