package com.example.androidtutorial2.themes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemThemeBinding

class ThemesViewHolder(
    parentView: ViewGroup, val binding: ItemThemeBinding = ItemThemeBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(theme: String) {
        binding.tvThemeName.text = theme
    }
}
