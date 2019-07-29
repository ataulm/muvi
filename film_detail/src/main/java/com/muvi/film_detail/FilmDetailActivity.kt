package com.muvi.film_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.muvi.base_domain.Film
import com.muvi.film_detail.di.inject
import com.muvi.navigation.actorDetailIntent
import com.muvi.navigation.extractFilmId
import kotlinx.android.synthetic.main.activity_film_detail.*
import javax.inject.Inject

/**
 * TODO: show:
 *
 * - poster
 * - backdrop
 * - description
 * - year
 * - directors
 * - title
 * - list of characters
 *      - image (optional)
 *      - actor name
 *      - character name
 */
class FilmDetailActivity : AppCompatActivity() {

    @Inject
    internal lateinit var filmDetailViewModel: FilmDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)

        setContentView(R.layout.activity_film_detail)

        Log.d("!!", "activity film detail: ${intent.extractFilmId()}")

        val button = firstActorButton
        filmDetailViewModel.film.observe(this, Observer<Film> { film ->
            film?.characters?.first()?.let {
                val actor = it.actor
                button.text = actor.name
                button.setOnClickListener {
                    startActivity(actorDetailIntent(actor.id))
                }
            }
        })
        filmDetailViewModel.loadFilm(intent.extractFilmId())
    }
}
