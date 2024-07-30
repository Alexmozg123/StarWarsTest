package com.example.starwarstest.ui.screens.favourites

import com.example.starwarstest.domain.model.UIModel

sealed class FavouriteViewResult {
    data class Successes(val resultList: List<UIModel>) : FavouriteViewResult()
    data object Loading : FavouriteViewResult()
}