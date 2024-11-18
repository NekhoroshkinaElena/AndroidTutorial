package com.example.androidtutorial2.repeat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemThemeRepeatBinding
import com.example.androidtutorial2.themes.domain.model.Theme

class RepeatThemesViewHolder(
    private val parentView: ViewGroup,
    private val binding: ItemThemeRepeatBinding = ItemThemeRepeatBinding.inflate(
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
        binding.countSubThemes.text = theme.subThemesCount.toString()

        if (theme.isThemeInStudy) {
            binding.cvThemeName.setCardBackgroundColor(parentView.context.getColor(R.color.light_yellow))
        }

        if (theme.isThemeCompleted) {
            binding.cvThemeName.setCardBackgroundColor(parentView.context.getColor(R.color.light_green))
        }

        if (!theme.isThemeInStudy && !theme.isThemeCompleted) {
            binding.cvThemeName.setCardBackgroundColor(parentView.context.getColor(R.color.white))
        }
    }
}
