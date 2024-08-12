package com.example.androidtutorial2.themes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemThemeBinding
import com.example.androidtutorial2.themes.domain.model.Theme

class ThemesViewHolder(
    private val parentView: ViewGroup,
    private val binding: ItemThemeBinding = ItemThemeBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(theme: Theme) {
        if (theme.blocked) {
            binding.cvThemeName.setCardBackgroundColor(
                ContextCompat.getColor(
                    parentView.context,
                    R.color.platinum
                )
            )

            binding.ivLock.isVisible = true
        }
        binding.tvThemeName.text = theme.name
    }
}
