package com.tree.nuts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tree.nuts.feed.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val filmRepository: FilmRepository = AndroidFilmRepository.create(
            BuildConfig.API_KEY, BuildConfig.API_SECRET, object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    })
    private val getFilms = GetFilmsUseCase(filmRepository)

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(parentJob)

    fun test() {
        coroutineScope.launch(Dispatchers.IO) {
            val result = getFilms()
            Log.e("!!", result.toString())
        }
    }
}
