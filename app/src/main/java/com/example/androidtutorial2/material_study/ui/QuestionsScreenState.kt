package com.example.androidtutorial2.material_study.ui

import com.example.androidtutorial2.material_study.domain.Question
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

sealed class QuestionsScreenState {
    data class Content(
        val listQuestions: List<Question>,
        val cssStyle: String,
        val subTopic: SubTopic
    ) : QuestionsScreenState()

    data object Loading : QuestionsScreenState()
    data class Error(val message: String) : QuestionsScreenState()
}
