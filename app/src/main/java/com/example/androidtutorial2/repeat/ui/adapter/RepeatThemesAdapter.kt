package com.example.androidtutorial2.repeat.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.themes.domain.model.Theme

class RepeatThemesAdapter(val onClick: (Theme) -> Unit) :
    RecyclerView.Adapter<RepeatThemesViewHolder>() {

    var themes = mutableListOf<Theme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatThemesViewHolder {
        return RepeatThemesViewHolder(parent)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: RepeatThemesViewHolder, position: Int) {
        val theme = themes[position]
        holder.bind(theme)

        holder.itemView.setOnClickListener {
            onClick(theme)
        }
    }
}
