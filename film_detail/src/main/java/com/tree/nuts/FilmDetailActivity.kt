package com.tree.nuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("!!", "film detail: ${intent.getStringExtra("FILM_ID")}")
    }
}
