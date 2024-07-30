package com.example.starwarstest.ui.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.starwarstest.domain.StarWarsInteractor
import com.example.starwarstest.domain.model.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel(
    private val starWarsInteractor: StarWarsInteractor,
) : ViewModel() {

    val result: StateFlow<FavouriteViewResult> = starWarsInteractor.readFavourite()
        .map { FavouriteViewResult.Successes(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, FavouriteViewResult.Loading)

    fun onUnlikeClicked(model: UIModel) {
        viewModelScope.launch(Dispatchers.IO) { starWarsInteractor.doNotFavourite(model) }
    }

    @Suppress("UNCHECKED_CAST")
    class FavouriteVMFactory @Inject constructor(
        private val starWarsInteractor: StarWarsInteractor,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            FavouriteViewModel(starWarsInteractor) as T
    }
}