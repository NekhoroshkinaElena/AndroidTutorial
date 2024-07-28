package com.example.androidtutorial2.themes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemThemeBinding
import com.example.androidtutorial2.themes.domain.model.Theme

class ThemesViewHolder(
    parentView: ViewGroup, private val binding: ItemThemeBinding = ItemThemeBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(theme: Theme) {
        binding.tvThemeName.text = theme.name
    }
}
