package com.example.androidtutorial2.themes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.themes.domain.model.Theme

class ThemesAdapter(val onClick: (Theme) -> Unit) :
    RecyclerView.Adapter<ThemesViewHolder>() {

    var themes = mutableListOf<Theme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesViewHolder {
        return ThemesViewHolder(parent)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: ThemesViewHolder, position: Int) {
        val theme = themes[position]
        holder.bind(theme)

        holder.itemView.setOnClickListener {
            onClick(theme)
        }
    }
}
