package com.muvi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muvi.design_library.ListItem1View
import com.muvi.design_library.commaAmpersandList

internal class FilmSummaryAdapter : ListAdapter<UiModel, FilmSummaryAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        val model = ListItem1View.Model(
                poster = uiModel.poster,
                title = title(uiModel.title, uiModel.year, uiModel.directors),
                onClick = uiModel.onClick
        )
        (holder.itemView as ListItem1View).bind(model)
    }

    private fun title(title: String, year: CharSequence?, directors: List<String>): CharSequence {
        val titleBuilder = StringBuilder(title)
        year?.let { titleBuilder.append(" ($it)") }
        directors.commaAmpersandList()?.let { titleBuilder.append(" $it") }
        return titleBuilder.toString()
    }

    object Differ : DiffUtil.ItemCallback<UiModel>() {

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
