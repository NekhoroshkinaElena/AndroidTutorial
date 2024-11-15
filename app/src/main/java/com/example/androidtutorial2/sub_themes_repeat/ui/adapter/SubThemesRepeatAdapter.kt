package com.example.androidtutorial2.sub_themes_repeat.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

class SubThemesRepeatAdapter(
    val onClick: (SubTheme) -> Unit,
    val onMenuItemClick: (SubTheme, Int) -> Unit
) :
    RecyclerView.Adapter<SubThemesRepeatViewHolder>() {

    var subThemes = mutableListOf<SubTheme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubThemesRepeatViewHolder {
        return SubThemesRepeatViewHolder(parent)
    }

    override fun getItemCount(): Int = subThemes.size

    override fun onBindViewHolder(holder: SubThemesRepeatViewHolder, position: Int) {
        val subTheme = subThemes[position]
        holder.bind(subTheme)

        holder.itemView.setOnClickListener {
            onClick(subTheme)
        }

        holder.setMenuItemClickListener { itemId ->
            onMenuItemClick(subTheme, itemId)
        }
    }
}
