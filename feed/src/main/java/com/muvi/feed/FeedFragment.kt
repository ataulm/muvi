package com.muvi.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.muvi.base_domain.FilmSummary
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

// TODO: show list of films (like Letterboxd search)
internal class FeedFragment : Fragment() {

    private val feedViewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(feedModule)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = firstFilmButton
        feedViewModel.films.observe(this, Observer<FilmSummary> { film ->
            film?.let {
                button.text = film.title
                button.setOnClickListener {
                    startActivity(intent("com.muvi.film_detail.FilmDetailActivity", film.id))
                }
            }
        })
    }

    private fun intent(componentName: String, filmId: String): Intent {
        return Intent(Intent.ACTION_VIEW)
                .setClassName("com.muvi", componentName)
                .putExtra("FILM_ID", filmId)
    }
}
