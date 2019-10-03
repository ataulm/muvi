package com.muvi.design_library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ataulm.mdcshowcase.startMdcShowcase

class ThemePlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMdcShowcase(R.style.Theme_Muvi)
        finish()
    }
}
