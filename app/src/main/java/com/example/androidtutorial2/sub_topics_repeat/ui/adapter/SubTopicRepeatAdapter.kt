package com.example.androidtutorial2.sub_topics_repeat.ui.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

class SubTopicRepeatAdapter(
    val onClick: (SubTopic) -> Unit,
    val onMenuItemClick: (SubTopic, Int) -> Unit
) :
    RecyclerView.Adapter<SubTopicRepeatViewHolder>() {

    var subTopics = mutableListOf<SubTopic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicRepeatViewHolder {
        return SubTopicRepeatViewHolder(parent)
    }

    override fun getItemCount(): Int = subTopics.size

    override fun onBindViewHolder(holder: SubTopicRepeatViewHolder, position: Int) {
        val subTopic = subTopics[position]
        holder.bind(subTopic)

        holder.itemView.setOnClickListener {
            if (subTopic.isSelected){
                onClick(subTopic)
            } else {
                Toast.makeText(holder.itemView.context, "начните повторять, что бы перейти к материалу", Toast.LENGTH_SHORT).show()
            }
        }

        holder.setMenuItemClickListener { itemId ->
            onMenuItemClick(subTopic, itemId)
        }
    }
}
