package com.example.starwarstest.ui.screens.searchscreens

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

class SearchViewModel(
    private val starWarsInteractor: StarWarsInteractor,
) : ViewModel() {

    private val _result = MutableLiveData<List<UIModel>>()
    val result: LiveData<List<UIModel>>
        get() = _result

    fun onTextChanged(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = starWarsInteractor.searchPeopleAndStarshipsByName(name)
            if (list.isNotEmpty()) _result.postValue(list)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SearchVMFactory @Inject constructor(
        private val starWarsInteractor: StarWarsInteractor,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            SearchViewModel(starWarsInteractor) as T
    }
}
