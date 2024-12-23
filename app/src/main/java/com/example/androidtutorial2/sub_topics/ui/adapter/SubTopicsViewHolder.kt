package com.example.androidtutorial2.sub_topics.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemSubTopicBinding
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

class SubTopicsViewHolder(
    parentView: ViewGroup, private val binding: ItemSubTopicBinding = ItemSubTopicBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subTopic: SubTopic) {
        binding.tvSubTopicName.text = subTopic.name
    }
}
