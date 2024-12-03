package com.example.androidtutorial2.material_study.ui

import com.example.androidtutorial2.material_study.domain.Question
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

sealed class QuestionsScreenState {
    data class Content(
        val listQuestions: List<Question>,
        val cssStyle: String,
        val subTheme: SubTheme
    ) : QuestionsScreenState()

    data object Loading : QuestionsScreenState()
    data class Error(val message: String) : QuestionsScreenState()
}
