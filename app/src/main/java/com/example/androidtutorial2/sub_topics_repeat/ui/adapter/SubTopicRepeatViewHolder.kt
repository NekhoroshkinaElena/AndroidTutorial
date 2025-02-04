package com.example.androidtutorial2.sub_topics_repeat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemSubTopicRepeatBinding
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

class SubTopicRepeatViewHolder(
    private val parentView: ViewGroup,
    val binding: ItemSubTopicRepeatBinding = ItemSubTopicRepeatBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var subTopic: SubTopic

    fun bind(subTopic: SubTopic) {
        binding.tvSubTopicName.text = subTopic.name
        this.subTopic = subTopic

        if (subTopic.isSelected) {
            binding.tvNumberRepetitions.isVisible = true
            binding.tvNumberRepetitions.text =
                itemView.context.getString(R.string.repetition_format, subTopic.numberRepetitions)
            if (subTopic.numberRepetitions >= 6) {
                setCardBackgroundColor(R.color.light_green)
            } else {
                setCardBackgroundColor(R.color.light_yellow)
            }
        } else {
            setCardBackgroundColor(R.color.platinum)
            binding.tvNumberRepetitions.isVisible = false
        }
    }

    fun setMenuItemClickListener(onClick: (Int) -> Unit) {
        binding.ivSubTopicMenu.setOnClickListener {
            showPopupMenu(it, onClick)
        }
    }

    private fun showPopupMenu(view: View, onClick: (Int) -> Unit) {
        PopupMenu(itemView.context, view).apply {
            menuInflater.inflate(R.menu.item_sub_topic_menu, menu)

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
                    subTopic.numberRepetitions
                )
                if (subTopic.numberRepetitions >= 7) {
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
        binding.cvSubTopic.setCardBackgroundColor(
            ContextCompat.getColor(
                itemView.context,
                color
            )
        )
    }
}
