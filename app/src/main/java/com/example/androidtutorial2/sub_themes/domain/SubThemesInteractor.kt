package com.example.androidtutorial2.sub_themes.domain

interface SubThemesInteractor {

    suspend fun getAllSubThemes(): List<SubTheme>

    suspend fun getSubThemes(themeId: Int): List<SubTheme>
}
