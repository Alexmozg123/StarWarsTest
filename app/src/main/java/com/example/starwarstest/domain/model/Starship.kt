package com.example.starwarstest.domain.model

data class Starship(
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: Int,
)