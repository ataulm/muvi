package com.muvi.actor_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.actor_detail_domain.ActorDetail
import com.muvi.actor_detail_domain.CharacterInFilm
import com.muvi.actor_detail_domain.GetActorDetailUseCase
import com.muvi.navigation.EventWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ActorDetailViewModel(
        private val actorId: String,
        private val getActorDetail: GetActorDetailUseCase
) : ViewModel() {

    private val _actorDetail = MutableLiveData<UiModel>()
    val actorDetail: LiveData<UiModel> = _actorDetail

    private val _events = MutableLiveData<EventWrapper<Event>>()
    val events: LiveData<EventWrapper<Event>> = _events

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val uiModel = getActorDetail(actorId).toUiModel()
            withContext(Dispatchers.Main) {
                _actorDetail.value = uiModel
            }
        }
    }

    private fun ActorDetail.toUiModel() = UiModel(
            films = films.map { it.toUiModelFilm() }
    )

    private fun CharacterInFilm.toUiModelFilm() = UiModel.Film(
            id = filmSummary.id,
            characterName = characterName,
            title = filmSummary.title,
            year = filmSummary.year?.toString(),
            directors = filmSummary.directors,
            // TALK: Simplification for demo. We should map this so it can go to the View so it can choose the correct width/height
            poster = filmSummary.poster?.sizes?.first()?.url,
            onClick = { _events.value = EventWrapper(Event.NavigateToFilmDetail(filmSummary.id)) }
    )

    sealed class Event {
        data class NavigateToFilmDetail(val filmId: String) : Event()
    }
}

internal data class UiModel(
        // TODO: we want more actor info here (name, image, description)
        val films: List<Film>
) {

    data class Film(
            val id: String,
            val characterName: String?,
            val title: String,
            val year: String?,
            val directors: List<String>,
            val poster: String?,
            val onClick: () -> Unit
    )
}
