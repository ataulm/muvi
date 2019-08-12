package com.muvi.actor_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.muvi.navigation.EventObserver
import com.muvi.navigation.extractActorId
import com.muvi.navigation.filmDetailIntent
import kotlinx.android.synthetic.main.activity_actor_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class ActorDetailActivity : AppCompatActivity(R.layout.activity_actor_detail) {

    private val actorDetailViewModel: ActorDetailViewModel by viewModel { parametersOf(intent.extractActorId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(actorDetailModule)

        val adapter = ActorDetailFilmAdapter()
        recyclerView.adapter = adapter
        actorDetailViewModel.actorDetail.observe(this, Observer<UiModel> { actorDetail ->
            adapter.submitList(actorDetail.films)
        })

        actorDetailViewModel.events.observe(this, EventObserver {
            when (it) {
                is ActorDetailViewModel.Event.NavigateToFilmDetail -> startActivity(filmDetailIntent(it.filmId))
            }
        })
    }
}
