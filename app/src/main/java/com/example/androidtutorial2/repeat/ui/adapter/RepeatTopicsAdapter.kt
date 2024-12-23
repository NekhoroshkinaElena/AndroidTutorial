package com.example.androidtutorial2.repeat.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.topic.domain.model.Topic

class RepeatTopicsAdapter(val onClick: (Topic) -> Unit) :
    RecyclerView.Adapter<RepeatTopicsViewHolder>() {

    var topics = mutableListOf<Topic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatTopicsViewHolder {
        return RepeatTopicsViewHolder(parent)
    }

    override fun getItemCount(): Int = topics.size

    override fun onBindViewHolder(holder: RepeatTopicsViewHolder, position: Int) {
        val topic = topics[position]
        holder.bind(topic)

        holder.itemView.setOnClickListener {
            onClick(topic)
        }
    }
}
