package com.example.androidtutorial2.themes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ThemesAdapter : RecyclerView.Adapter<ThemesViewHolder>() {

    var themes = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesViewHolder {
        return ThemesViewHolder(parent)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: ThemesViewHolder, position: Int) {
        val theme = themes[position]
        holder.bind(theme)
    }
}
