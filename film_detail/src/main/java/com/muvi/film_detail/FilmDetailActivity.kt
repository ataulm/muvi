package com.muvi.film_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class FilmDetailActivity : AppCompatActivity() {

    private val filmDetailViewModel: FilmDetailViewModel by viewModel { parametersOf(filmId) }
    private lateinit var filmId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(filmDetailModule)

        filmId = intent.getStringExtra("FILM_ID")
        Log.d("!!", "activity film detail: $filmId")

        filmDetailViewModel.foo()
    }
}
