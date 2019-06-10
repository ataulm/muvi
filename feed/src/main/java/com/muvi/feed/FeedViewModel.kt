package com.muvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.feed_domain.Film
import com.muvi.feed_domain.GetFilmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class FeedViewModel(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val _films = MutableLiveData<Film>()
    val films: LiveData<Film> = _films

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getFilms()
            withContext(Dispatchers.Main) {
                _films.value = result.first()
            }
        }
    }
}
