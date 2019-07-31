package com.muvi.film_detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muvi.base_domain.Image
import com.muvi.film_detail.R
import kotlinx.android.synthetic.main.film_detail_item_character.view.*
import kotlinx.android.synthetic.main.film_detail_item_header.view.*

internal class FilmDetailAdapter() : ListAdapter<FilmDetailAdapter.Item, BoringViewHolder>(Differ) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Item.Header -> R.layout.film_detail_item_header
        is Item.Character -> R.layout.film_detail_item_character
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int): BoringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return BoringViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoringViewHolder, position: Int) = getItem(position).let { item ->
        when (item) {
            is Item.Header -> holder.bind(item)
            is Item.Character -> holder.bind(item)
        }
    }

    object Differ : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item) = when {
            oldItem is Item.Header && newItem is Item.Header -> true
            oldItem is Item.Character && newItem is Item.Character -> oldItem.name == newItem.name
            else -> false
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }

    sealed class Item {

        data class Header(
                val title: CharSequence,
                val backdrop: Image?,
                val poster: Image?,
                val year: CharSequence?,
                val directors: CharSequence?,
                val description: CharSequence?
        ) : Item()

        data class Character(
                val name: CharSequence,
                val actorName: CharSequence,
                val onClick: () -> Unit
        ) : Item()
    }
}

private fun BoringViewHolder.bind(header: FilmDetailAdapter.Item.Header) {
    val backdrop = header.backdrop?.sizes?.firstOrNull()
    val poster = header.poster?.sizes?.lastOrNull()
    val glide = Glide.with(itemView)
    glide.load(backdrop?.url).into(itemView.backdropImageView)
    glide.load(poster?.url).into(itemView.posterImageView)
    itemView.titleTextView.text = header.title
    itemView.directorLabelTextView.visibility = if (header.directors == null) GONE else VISIBLE
    itemView.directorsTextView.textOrGone(header.directors)
    itemView.yearTextView.textOrGone(header.year)
    itemView.descriptionTextView.textOrGone(header.description)
}

private fun TextView.textOrGone(newText: CharSequence?) {
    text = newText
    visibility = if (newText == null) GONE else VISIBLE
}

private fun BoringViewHolder.bind(character: FilmDetailAdapter.Item.Character) {
    itemView.characterTextView.text = character.name
    itemView.actorTextView.text = character.actorName
}

internal class BoringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
