package com.muvi.film_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.muvi.base_domain.Film
import com.muvi.film_detail.di.inject
import com.muvi.film_detail.ui.FilmDetailAdapter
import com.muvi.navigation.actorDetailIntent
import com.muvi.navigation.extractFilmId
import kotlinx.android.synthetic.main.activity_film_detail.*
import javax.inject.Inject

class FilmDetailActivity : AppCompatActivity() {

    @Inject
    internal lateinit var filmDetailViewModel: FilmDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filmId = intent.extractFilmId()
        inject(this, filmId)

        setContentView(R.layout.activity_film_detail)

        val adapter = FilmDetailAdapter()
        recyclerView.adapter = adapter
        filmDetailViewModel.film.observe(this, Observer<Film> { film ->
            film?.toUiModel()?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun Film.toUiModel(): List<FilmDetailAdapter.Item> {
        val header = FilmDetailAdapter.Item.Header(
                title = title,
                backdrop = backdrop,
                poster = poster,
                directors = directors(),
                year = year?.toString(),
                description = description
        )
        val charactersUiModel = characters.map { character ->
            FilmDetailAdapter.Item.Character(
                    name = character.name,
                    actorName = character.actor.name,
                    onClick = { startActivity(actorDetailIntent(character.actor.id)) }
            )
        }
        return listOf(header) + charactersUiModel
    }

    private fun Film.directors() = when (directors.size) {
        0 -> "Mr Director" // TODO: should be nullable in this case I guess
        1 -> directors[0]
        else -> {
            val firstLot = directors.subList(0, directors.size - 1).joinToString(separator = ", ")
            firstLot + " & " + directors.last()
        }
    }
}
