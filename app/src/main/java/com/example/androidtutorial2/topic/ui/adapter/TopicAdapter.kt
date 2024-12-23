package com.example.androidtutorial2.topic.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.topic.domain.model.Topic

class TopicAdapter(val onClick: (Topic) -> Unit) :
    RecyclerView.Adapter<TopicsViewHolder>() {

    var topics = mutableListOf<Topic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        return TopicsViewHolder(parent)
    }

    override fun getItemCount(): Int = topics.size

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        val topic = topics[position]
        holder.bind(topic)

        holder.itemView.setOnClickListener {
            onClick(topic)
        }
    }
}
