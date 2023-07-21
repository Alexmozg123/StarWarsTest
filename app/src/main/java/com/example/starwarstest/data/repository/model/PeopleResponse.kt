package com.example.starwarstest.data.repository.model

data class PeopleResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PeopleBin>
)