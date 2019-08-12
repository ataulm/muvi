package com.muvi

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.muvi.navigation.themePlaygroundIntent

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_theme_playground -> {
                startActivity(themePlaygroundIntent())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
