package com.muvi.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.muvi.base_domain.FilmSummary
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

        val adapter = FilmSummaryAdapter {
            startActivity(filmDetailIntent(it.id))
        }
        recyclerView.adapter = adapter
        feedViewModel.films.observe(this, Observer<List<FilmSummary>> { films ->
            films?.let { adapter.submitList(it) }
        })
    }
}
