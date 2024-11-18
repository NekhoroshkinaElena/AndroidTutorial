package com.example.androidtutorial2.sub_themes.domain

import com.example.androidtutorial2.sub_themes.domain.model.SubTheme
import javax.inject.Inject

class SubThemeInteractorImpl @Inject constructor(
    private val subThemesRepository: SubThemesRepository
) : SubThemesInteractor {

    override suspend fun getAllSubThemes(): List<SubTheme> {
        return subThemesRepository.getAllSubThemes()
    }

    override suspend fun getSubThemeById(subThemeId: Int): SubTheme? {
        return subThemesRepository.getSubThemeById(subThemeId)
    }

    override suspend fun getSubThemes(themeId: Int): List<SubTheme> {
        return subThemesRepository.getSubThemes(themeId)
    }

    override suspend fun updateNumberRepetitions(subThemeId: Int, numberRepetitions: Int) {
        subThemesRepository.updateNumberRepetitions(subThemeId, numberRepetitions)
    }

    override suspend fun updateSelectionState(subThemeId: Int, isSelected: Boolean) {
        subThemesRepository.updateSelectionState(subThemeId, isSelected)
    }

    override suspend fun resetProgress(subThemeId: Int) {
        subThemesRepository.resetProgress(subThemeId)
    }
}
