package com.muvi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film_summary.view.*

internal class FilmSummaryAdapter : ListAdapter<FilmSummaryUiModel, FilmSummaryAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmSummary = getItem(position)
        (holder.itemView).apply {
            Glide.with(this)
                    .load(filmSummary.poster?.sizes?.lastOrNull()?.url)
                    .into(posterImageView)
            summaryTextView.text = filmSummary.title
            setOnClickListener { filmSummary.onClick() }
        }
    }

    object Differ : DiffUtil.ItemCallback<FilmSummaryUiModel>() {

        override fun areItemsTheSame(oldItem: FilmSummaryUiModel, newItem: FilmSummaryUiModel) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FilmSummaryUiModel, newItem: FilmSummaryUiModel) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
