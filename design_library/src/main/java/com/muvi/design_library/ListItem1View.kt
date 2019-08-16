package com.muvi.design_library

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.merge_list_item_1.view.*

class ListItem1View(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        View.inflate(context, R.layout.merge_list_item_1, this)
    }

    fun bind(model: Model) {
        Glide.with(this)
                .load(model.poster)
                .into(posterImageView)
        summaryTextView.text = model.title
        setOnClickListener { model.onClick() }
    }

    data class Model(
            val poster: String?,
            val title: CharSequence,
            val onClick: () -> Unit
    )
}
