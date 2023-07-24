package com.example.starwarstest.domain.model

sealed class UIModel {

    class PeopleModel(val people: People) : UIModel()
    class StarshipModel(val starship: Starship) : UIModel()
}