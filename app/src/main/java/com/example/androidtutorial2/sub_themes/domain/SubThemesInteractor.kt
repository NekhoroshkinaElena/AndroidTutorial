package com.example.androidtutorial2.sub_themes.domain

import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

interface SubThemesInteractor {

    suspend fun getAllSubThemes(): List<SubTheme>

    suspend fun  getSubThemeById(subThemeId: Int): SubTheme?

    suspend fun getSubThemes(themeId: Int): List<SubTheme>

    suspend fun updateNumberRepetitions(subThemeId: Int, numberRepetitions: Int)

    suspend fun updateSelectionState(subThemeId: Int, isSelected: Boolean)

    suspend fun resetProgress(subThemeId: Int)
}
