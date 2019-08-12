package com.muvi.actor_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muvi.design_library.FilmSummaryView

internal class ActorDetailFilmAdapter : ListAdapter<UiModel.Film, ActorDetailFilmAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        val model = FilmSummaryView.Model(
                title = if (uiModel.characterName != null) "${uiModel.characterName} in ${uiModel.title}" else uiModel.title,
                year = uiModel.year,
                poster = uiModel.poster,
                directors = uiModel.directors,
                onClick = uiModel.onClick
        )
        (holder.itemView as FilmSummaryView).bind(model)
    }

    object Differ : DiffUtil.ItemCallback<UiModel.Film>() {

        override fun areItemsTheSame(oldItem: UiModel.Film, newItem: UiModel.Film) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UiModel.Film, newItem: UiModel.Film) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
