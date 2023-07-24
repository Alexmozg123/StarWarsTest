package com.example.starwarstest.ui.screens.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.starwarstest.domain.StarWarsInteractor
import com.example.starwarstest.domain.model.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel(
    private val starWarsInteractor: StarWarsInteractor,
) : ViewModel() {

    private val _result = MutableLiveData<List<UIModel>>()
    val result: LiveData<List<UIModel>>
        get() = _result

    fun onStartViewModel() {
        updateFavouriteList()
    }

    fun onUnlikeClicked(model: UIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            starWarsInteractor.doNotFavourite(model)
            updateFavouriteList()
        }
    }

    private fun updateFavouriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = starWarsInteractor.readFavourite()
            if (list.isNotEmpty()) _result.postValue(list)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class FavouriteVMFactory @Inject constructor(
        private val starWarsInteractor: StarWarsInteractor,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            FavouriteViewModel(starWarsInteractor) as T
    }
}