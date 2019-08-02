package com.muvi.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.muvi.base_domain.FilmSummary
import com.muvi.feed_domain.GetFilmsUseCase
import com.muvi.navigation.EventWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FeedViewModel @Inject constructor(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val _films = MutableLiveData<List<UiModel>>()
    val films: LiveData<List<UiModel>> = _films

    private val _events = MutableLiveData<EventWrapper<Event>>()
    val events: LiveData<EventWrapper<Event>> = _events

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val uiModels = getFilms().map { filmSummary ->
                UiModel(filmSummary) {
                    _events.value = EventWrapper(Event.NavigateToFilmDetail(filmSummary.id))
                }
            }
            withContext(Dispatchers.Main) {
                _films.value = uiModels
            }
        }
    }

    sealed class Event {
        data class NavigateToFilmDetail(val filmId: String) : Event()
    }
}

internal class FeedViewModelFactory @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = FeedViewModel(getFilmsUseCase) as T
}

internal data class UiModel(
        val filmSummary: FilmSummary,
        val onClick: () -> Unit
)
