package com.example.androidtutorial2.material_study.domain

import javax.inject.Inject

class MaterialStudyInteractorImpl @Inject constructor(
    private val materialStudyRepository: MaterialStudyRepository
) : MaterialStudyInteractor {

    override suspend fun getQuestions(subThemeId: Int): List<Question> {
        return materialStudyRepository.getQuestions(subThemeId)
    }
}
