package com.example.androidtutorial2.topic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.ItemTopicBinding
import com.example.androidtutorial2.topic.domain.model.Topic

class TopicsViewHolder(
    private val parentView: ViewGroup,
    private val binding: ItemTopicBinding = ItemTopicBinding.inflate(
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
    }
}
