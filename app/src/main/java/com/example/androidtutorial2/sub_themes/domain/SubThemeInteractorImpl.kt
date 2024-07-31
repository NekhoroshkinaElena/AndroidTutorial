package com.example.androidtutorial2.sub_themes.domain

import javax.inject.Inject

class SubThemeInteractorImpl @Inject constructor(
    private val subThemesRepository: SubThemesRepository
) : SubThemesInteractor {

    override suspend fun getAllSubThemes(): List<SubTheme> {
        return subThemesRepository.getAllSubThemes()
    }

    override suspend fun getSubThemes(themeId: Int): List<SubTheme> {
        return subThemesRepository.getSubThemes(themeId)
    }
}
