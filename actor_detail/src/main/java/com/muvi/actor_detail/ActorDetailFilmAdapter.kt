package com.muvi.actor_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muvi.design_library.ListItem1View
import com.muvi.design_library.commaAmpersandList
import com.muvi.design_library.R as designLibrary

internal class ActorDetailFilmAdapter : ListAdapter<UiModel.Film, ActorDetailFilmAdapter.ViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(designLibrary.layout.item_film_summary, parent, false)
            .run { ViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        val model = ListItem1View.Model(
                poster = uiModel.poster,
                title = title(uiModel.title, uiModel.characterName, uiModel.year, uiModel.directors),
                onClick = uiModel.onClick
        )
        (holder.itemView as ListItem1View).bind(model)
    }

    private fun title(title: String, characterName: CharSequence?, year: CharSequence?, directors: List<String>): CharSequence {
        val titleBuilder = StringBuilder(title)
        characterName?.let { titleBuilder.append(" as ($it)") }
        year?.let { titleBuilder.append(" ($it)") }
        directors.commaAmpersandList()?.let { titleBuilder.append(" $it") }
        return titleBuilder.toString()
    }

    object Differ : DiffUtil.ItemCallback<UiModel.Film>() {

        override fun areItemsTheSame(oldItem: UiModel.Film, newItem: UiModel.Film) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UiModel.Film, newItem: UiModel.Film) = oldItem == newItem
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
