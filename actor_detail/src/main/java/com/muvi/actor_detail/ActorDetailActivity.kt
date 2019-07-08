package com.muvi.actor_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

/**
 * TODO: show same as FeedFragment but limited to actor's films
 */
class ActorDetailActivity : AppCompatActivity() {

    private val actorDetailViewModel: ActorDetailViewModel by viewModel { parametersOf(actorId) }
    private lateinit var actorId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(actorDetailModule)

        actorId = intent.getStringExtra("ACTOR_ID")
        Log.d("!!", "activity actor detail: $actorId")

        actorDetailViewModel.foo()
    }
}
