package com.example.starwarstest.data.repository.model

data class StarshipResponse(
    val count: Int,
    val next: Any?,
    val previous: Any?,
    val results: List<StarshipBin>
)