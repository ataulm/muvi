package com.muvi.film_detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.muvi.base_domain.Film
import kotlinx.android.synthetic.main.activity_film_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

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

    private val filmDetailViewModel: FilmDetailViewModel by viewModel { parametersOf(filmId) }
    private lateinit var filmId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(filmDetailModule)

        setContentView(R.layout.activity_film_detail)

        filmId = intent.getStringExtra("FILM_ID")
        Log.d("!!", "activity film detail: $filmId")

        val button = firstActorButton
        filmDetailViewModel.film.observe(this, Observer<Film> { film ->
            film?.characters?.first()?.let {
                val actor = it.actor
                button.text = actor.name
                button.setOnClickListener {
                    startActivity(intent("com.muvi.actor_detail.ActorDetailActivity", actor.id))
                }
            }
        })
    }

    private fun intent(componentName: String, filmId: String): Intent {
        return Intent(Intent.ACTION_VIEW)
                .setClassName("com.muvi", componentName)
                .putExtra("ACTOR_ID", filmId)
    }
}
