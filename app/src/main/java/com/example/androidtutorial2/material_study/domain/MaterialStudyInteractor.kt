package com.example.androidtutorial2.material_study.domain

interface MaterialStudyInteractor {

    suspend fun getQuestions(subTopicId: Int): List<Question>
}
