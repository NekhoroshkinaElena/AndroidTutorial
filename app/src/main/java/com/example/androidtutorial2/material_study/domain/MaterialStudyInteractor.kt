package com.example.androidtutorial2.material_study.domain

interface MaterialStudyInteractor {

    suspend fun getQuestions(subThemeId: Int): List<Question>
}
