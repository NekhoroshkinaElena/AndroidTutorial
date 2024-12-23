package com.example.androidtutorial2.material_study.data

import com.example.androidtutorial2.material_study.domain.MaterialStudyRepository
import com.example.androidtutorial2.material_study.domain.Question
import com.example.androidtutorial2.topic.data.db.TutorialDb
import javax.inject.Inject

class MaterialStudyRepositoryImpl @Inject constructor(private val db: TutorialDb) :
    MaterialStudyRepository {

    override suspend fun getQuestions(subTopicId: Int): List<Question> {
        return db.getQuestions().getQuestions(subTopicId).map {
            Question(it.question)
        }
    }
}
