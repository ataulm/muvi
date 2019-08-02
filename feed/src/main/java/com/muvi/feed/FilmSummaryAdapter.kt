package com.muvi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muvi.base_domain.FilmSummary
import kotlinx.android.synthetic.main.item_film_summary.view.*

internal class FilmSummaryAdapter : ListAdapter<UiModel, FilmSummaryAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        Glide.with(holder.itemView)
                .load(uiModel.filmSummary.poster?.sizes?.lastOrNull()?.url)
                .into(holder.itemView.posterImageView)
        holder.itemView.summaryTextView.text = uiModel.filmSummary.title()
        holder.itemView.setOnClickListener { uiModel.onClick() }
    }

    private fun FilmSummary.title(): CharSequence {
        val directors = directors()
        return when {
            year != null && directors != null -> "$title, $year, $directors"
            year != null -> "$title, $year"
            directors != null -> "$title, $directors"
            else -> title
        }
    }

    private fun FilmSummary.directors() = when (directors.size) {
        0 -> null
        1 -> directors[0]
        else -> {
            val firstLot = directors.subList(0, directors.size - 1).joinToString(separator = ", ")
            firstLot + " & " + directors.last()
        }
    }

    object Differ : DiffUtil.ItemCallback<UiModel>() {

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem.filmSummary.id == newItem.filmSummary.id

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
