package com.muvi.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.base_domain.FilmSummary
import com.muvi.feed_domain.GetFilmsUseCase
import com.muvi.navigation.EventWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class FeedViewModel(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val _films = MutableLiveData<List<FilmSummary>>()
    val films: LiveData<List<FilmSummary>> = _films

    private val _events = MutableLiveData<EventWrapper<Event>>()
    val events: LiveData<EventWrapper<Event>> = _events

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: should we map FilmSummary to a UiModel here or use it directly?
            val result = getFilms()
            withContext(Dispatchers.Main) {
                _films.value = result
            }
        }
    }

    fun onClickFilmSummary(filmSummary: FilmSummary) {
        _events.value = EventWrapper(Event.NavigateToFilmDetail(filmSummary.id))
    }

    sealed class Event {
        data class NavigateToFilmDetail(val filmId: String): Event()
    }
}
