package com.muvi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muvi.base_domain.FilmSummary
import com.muvi.design_library.FilmSummaryView

internal class FilmSummaryAdapter : ListAdapter<UiModel, FilmSummaryAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        val model = FilmSummaryView.Model(
                title = uiModel.title,
                year = uiModel.year,
                poster = uiModel.poster,
                directors = uiModel.directors,
                onClick = uiModel.onClick
        )
        (holder.itemView as FilmSummaryView).bind(model)
    }

    object Differ : DiffUtil.ItemCallback<UiModel>() {

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
