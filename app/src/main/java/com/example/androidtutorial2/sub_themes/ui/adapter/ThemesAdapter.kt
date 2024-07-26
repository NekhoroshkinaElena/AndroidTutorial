package com.example.androidtutorial2.sub_themes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.themes.domain.model.Theme

class SubThemesAdapter(val onClick: (SubTheme) -> Unit) :
    RecyclerView.Adapter<SubThemesViewHolder>() {

    var subthemes = mutableListOf<SubTheme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubThemesViewHolder {
        return SubThemesViewHolder(parent)
    }

    override fun getItemCount(): Int = subthemes.size

    override fun onBindViewHolder(holder: SubThemesViewHolder, position: Int) {
        val subtheme = subthemes[position]
        holder.bind(subtheme)

        holder.itemView.setOnClickListener {
            onClick(subtheme)
        }
    }
}
