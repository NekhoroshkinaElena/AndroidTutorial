package com.example.androidtutorial2.repeat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemTopicRepeatBinding
import com.example.androidtutorial2.topic.domain.model.Topic

class RepeatTopicsViewHolder(
    private val parentView: ViewGroup,
    private val binding: ItemTopicRepeatBinding = ItemTopicRepeatBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(topic: Topic) {
        if (topic.blocked) {
            binding.cvTopicName.setCardBackgroundColor(
                ContextCompat.getColor(
                    parentView.context,
                    R.color.platinum
                )
            )
            binding.ivLock.isVisible = true
        }
        binding.tvTopicName.text = topic.name
        binding.countSubTopics.text = topic.subTopicsCount.toString()

        if (topic.isTopicInStudy) {
            binding.cvTopicName.setCardBackgroundColor(parentView.context.getColor(R.color.light_yellow))
        }

        if (topic.isTopicCompleted) {
            binding.cvTopicName.setCardBackgroundColor(parentView.context.getColor(R.color.light_green))
        }

        if (!topic.isTopicInStudy && !topic.isTopicCompleted) {
            binding.cvTopicName.setCardBackgroundColor(parentView.context.getColor(R.color.white))
        }
    }
}
