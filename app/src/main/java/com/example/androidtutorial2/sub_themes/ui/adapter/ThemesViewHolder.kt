package com.example.androidtutorial2.sub_themes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemSubThemesBinding
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

class SubThemesViewHolder(
    parentView: ViewGroup, private val binding: ItemSubThemesBinding = ItemSubThemesBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subtheme: SubTheme) {
        binding.tvSubThemeName.text = subtheme.name
    }
}
