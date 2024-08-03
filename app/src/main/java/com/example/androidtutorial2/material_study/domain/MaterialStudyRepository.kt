package com.example.androidtutorial2.material_study.domain

interface MaterialStudyRepository {

    suspend fun getQuestions(subThemeId: Int): List<Question>
}
