package com.muvi.film_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.film_detail.domain.FilmDetail
import com.muvi.film_detail.domain.GetFilmDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class FilmDetailViewModel(
        private val filmId: String,
        private val getFilms: GetFilmDetailUseCase
) : ViewModel() {
    fun foo() {

    }

    private val _filmDetail = MutableLiveData<FilmDetail>()
    val filmDetail: LiveData<FilmDetail> = _filmDetail

    init {
        Log.d("!!", "vm film detail: $filmId")
        viewModelScope.launch(Dispatchers.IO) {
            val result = getFilms(filmId)
            withContext(Dispatchers.Main) {
                _filmDetail.value = result
            }
        }
    }
}
