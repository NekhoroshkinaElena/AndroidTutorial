package com.example.androidtutorial2.sub_themes_repeat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemSubThemesRepeatBinding
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

class SubThemesRepeatViewHolder(
    private val parentView: ViewGroup,
    val binding: ItemSubThemesRepeatBinding = ItemSubThemesRepeatBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var subtheme: SubTheme

    fun bind(subTheme: SubTheme) {
        binding.tvSubThemeName.text = subTheme.name
        subtheme = subTheme

        if (subTheme.isSelected) {
            binding.tvNumberRepetitions.isVisible = true
            binding.tvNumberRepetitions.text =
                itemView.context.getString(R.string.repetition_format, subTheme.numberRepetitions)
            if (subTheme.numberRepetitions >= 7) {
                setCardBackgroundColor(R.color.light_green)
            } else {
                setCardBackgroundColor(R.color.light_yellow)
            }
        } else {
            setCardBackgroundColor(R.color.white)
            binding.tvNumberRepetitions.isVisible = false
        }
    }

    fun setMenuItemClickListener(onClick: (Int) -> Unit) {
        binding.ivSubThemeMenu.setOnClickListener {
            showPopupMenu(it, onClick)
        }
    }

    private fun showPopupMenu(view: View, onClick: (Int) -> Unit) {
        PopupMenu(itemView.context, view).apply {
            menuInflater.inflate(R.menu.item_sub_themes_menu, menu)

            setOnMenuItemClickListener { item ->
                handleMenuItemClick(item.itemId)
                onClick(item.itemId)
                true
            }
        }.show()
    }

    private fun handleMenuItemClick(itemId: Int) {
        when (itemId) {
            R.id.option_repeat -> {
                binding.tvNumberRepetitions.isVisible = true
                binding.tvNumberRepetitions.text = itemView.context.getString(
                    R.string.repetition_format,
                    subtheme.numberRepetitions
                )
                if (subtheme.numberRepetitions >= 7) {
                    setCardBackgroundColor(R.color.light_green)
                } else {
                    setCardBackgroundColor(R.color.light_yellow)
                }
            }

            R.id.option_repeat_later, R.id.option_reset_progress -> {
                binding.tvNumberRepetitions.isVisible = false
                setCardBackgroundColor(R.color.white)
            }

            else -> {
                setCardBackgroundColor(R.color.white)
            }
        }
    }

    private fun setCardBackgroundColor(color: Int) {
        binding.cvSubTheme.setCardBackgroundColor(
            ContextCompat.getColor(
                itemView.context,
                color
            )
        )
    }
}
