package com.muvi.film_detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
                val directors: CharSequence,
                val year: CharSequence?,
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
    itemView.titleTextView.text = header.title
    // TODO: bind the rest
}

private fun BoringViewHolder.bind(character: FilmDetailAdapter.Item.Character) {
    itemView.characterTextView.text = character.name
    itemView.actorTextView.text = character.actorName
}

internal class BoringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
