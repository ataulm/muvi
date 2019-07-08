package com.muvi.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.muvi.base_domain.FilmSummary
import com.muvi.navigation.EventObserver
import com.muvi.navigation.filmDetailIntent
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

        val adapter = FilmSummaryAdapter()
        recyclerView.adapter = adapter
        feedViewModel.films.observe(this, Observer<List<FilmSummaryUiModel>> { films ->
            films?.let { adapter.submitList(it) }
        })

        feedViewModel.events.observe(this, EventObserver {
            when (it) {
                is FeedViewModel.Event.NavigateToFilmDetail -> startActivity(filmDetailIntent(it.filmId))
            }
        })
    }
}
