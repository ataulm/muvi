package com.muvi.film_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.muvi.base_domain.Film
import com.muvi.film_detail.domain.GetFilmDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FilmDetailViewModel(
        private val getFilm: GetFilmDetailUseCase
) : ViewModel() {

    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film> = _film

    fun loadFilm(filmId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getFilm(filmId)
            Log.d("!!", "vm film detail: $result")
            withContext(Dispatchers.Main) {
                _film.value = result
            }
        }
    }
}

internal class FilmDetailViewModelFactory @Inject constructor(
    private val getFilmsUseCase: GetFilmDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = FilmDetailViewModel(getFilmsUseCase) as T
}
