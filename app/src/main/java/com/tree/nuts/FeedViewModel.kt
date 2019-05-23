package com.tree.nuts

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class FeedViewModel(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(parentJob)

    fun test() {
        coroutineScope.launch(Dispatchers.IO) {
            val result = getFilms()
            Log.e("!!", result.toString())
        }
    }
}
