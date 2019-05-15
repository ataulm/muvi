package com.tree.nuts.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tree.nuts.BuildConfig
import com.tree.nuts.Clock
import com.tree.nuts.LetterboxdApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val letterBoxdApi = LetterboxdApiFactory(
            BuildConfig.API_KEY, BuildConfig.API_SECRET, object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    })
    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(parentJob)

    fun test() {
        coroutineScope.launch(Dispatchers.IO) {
            val result = letterBoxdApi.remoteLetterboxdApi().films().await()
            Log.e("!!", result.toString())
        }
    }
}
