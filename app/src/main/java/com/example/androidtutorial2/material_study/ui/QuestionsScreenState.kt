package com.example.androidtutorial2.material_study.ui

import com.example.androidtutorial2.material_study.domain.Question

sealed class QuestionsScreenState {
    data class Content(val listQuestions: List<Question>) : QuestionsScreenState()
    data object Loading : QuestionsScreenState()
    data object Error : QuestionsScreenState()
}
