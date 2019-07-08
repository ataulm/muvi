package com.muvi.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muvi.base_domain.FilmSummary

internal class FilmSummaryAdapter(private val onClick: (FilmSummary) -> Unit) : ListAdapter<FilmSummary, FilmSummaryAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmSummary = getItem(position)
        (holder.itemView as TextView).apply {
            text = filmSummary.title
            setOnClickListener { onClick(filmSummary) }
        }
    }

    object Differ : DiffUtil.ItemCallback<FilmSummary>() {

        override fun areItemsTheSame(oldItem: FilmSummary, newItem: FilmSummary) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FilmSummary, newItem: FilmSummary) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
