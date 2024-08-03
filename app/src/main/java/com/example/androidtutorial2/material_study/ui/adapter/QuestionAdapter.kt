package com.example.androidtutorial2.material_study.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemQuestionBinding
import com.example.androidtutorial2.material_study.domain.Question

class QuestionAdapter(val onClick: (Question, ItemQuestionBinding) -> Unit) :
    RecyclerView.Adapter<QuestionsViewHolder>() {

    var questions = mutableListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(parent)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val questions = questions[position]
        holder.bind(questions)

        holder.itemView.setOnClickListener {
            onClick(questions, holder.binding)
        }
    }
}
