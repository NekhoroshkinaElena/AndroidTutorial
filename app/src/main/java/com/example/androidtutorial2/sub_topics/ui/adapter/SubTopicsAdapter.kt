package com.example.androidtutorial2.sub_topics.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

class SubTopicsAdapter(val onClick: (SubTopic) -> Unit) :
    RecyclerView.Adapter<SubTopicsViewHolder>() {

    var subTopics = mutableListOf<SubTopic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicsViewHolder {
        return SubTopicsViewHolder(parent)
    }

    override fun getItemCount(): Int = subTopics.size

    override fun onBindViewHolder(holder: SubTopicsViewHolder, position: Int) {
        val subTopic = subTopics[position]
        holder.bind(subTopic)

        holder.itemView.setOnClickListener {
            onClick(subTopic)
        }
    }
}
