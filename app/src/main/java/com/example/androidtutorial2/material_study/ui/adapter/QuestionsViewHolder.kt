package com.example.androidtutorial2.material_study.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtutorial2.databinding.ItemQuestionBinding
import com.example.androidtutorial2.material_study.domain.Question

class QuestionsViewHolder(
    parentView: ViewGroup, val binding: ItemQuestionBinding = ItemQuestionBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(question: Question) {
        binding.tvQuestion.text = question.question
    }
}
