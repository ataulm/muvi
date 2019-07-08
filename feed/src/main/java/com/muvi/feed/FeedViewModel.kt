package com.muvi.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.base_domain.FilmSummary
import com.muvi.feed_domain.GetFilmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class FeedViewModel(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val _films = MutableLiveData<List<FilmSummary>>()
    val films: LiveData<List<FilmSummary>> = _films

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: should we map FilmSummary to a UiModel here or use it directly?
            val result = getFilms()
            withContext(Dispatchers.Main) {
                _films.value = result
            }
        }
    }
}
