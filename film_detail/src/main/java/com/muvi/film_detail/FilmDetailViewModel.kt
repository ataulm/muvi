package com.muvi.film_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.base_domain.Film
import com.muvi.film_detail.domain.GetFilmDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class FilmDetailViewModel(
        private val filmId: String,
        private val getFilm: GetFilmDetailUseCase
) : ViewModel() {
    fun foo() {

    }

    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film> = _film

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getFilm(filmId)
            Log.d("!!", "vm film detail: $result")
            withContext(Dispatchers.Main) {
                _film.value = result
            }
        }
    }
}
