package com.muvi.design_library

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.merge_film_summary.view.*

class FilmSummaryView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        View.inflate(context, R.layout.merge_film_summary, this)
    }

    fun bind(model: Model) {
        Glide.with(this)
                .load(model.poster)
                .into(posterImageView)
        summaryTextView.text = title(model.title, model.year, model.directors)
        setOnClickListener { model.onClick() }
    }

    private fun title(title: String, year: CharSequence?, directors: List<String>): CharSequence {
        val directorsText = directorsText(directors)
        return when {
            year != null && directorsText != null -> "$title ($year) $directorsText"
            year != null -> "$title ($year)"
            directorsText != null -> "$title, $directorsText"
            else -> title
        }
    }

    private fun directorsText(directors: List<String>) = when (directors.size) {
        0 -> null
        1 -> directors[0]
        else -> {
            val firstLot = directors.subList(0, directors.size - 1).joinToString(separator = ", ")
            firstLot + " & " + directors.last()
        }
    }

    data class Model(
            val title: String,
            val poster: String?,
            val year: String?,
            val directors: List<String>,
            val onClick: () -> Unit
    )
}
