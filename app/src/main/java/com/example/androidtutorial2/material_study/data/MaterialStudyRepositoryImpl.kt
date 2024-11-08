package com.example.androidtutorial2.material_study.data

import com.example.androidtutorial2.material_study.domain.MaterialStudyRepository
import com.example.androidtutorial2.material_study.domain.Question
import com.example.androidtutorial2.themes.data.db.TutorialDb
import javax.inject.Inject

class MaterialStudyRepositoryImpl @Inject constructor(private val db: TutorialDb) :
    MaterialStudyRepository {

    override suspend fun getQuestions(subThemeId: Int): List<Question> {
        return db.getQuestions().getQuestions(subThemeId).map {
            Question(it.question)
        }
    }
}
