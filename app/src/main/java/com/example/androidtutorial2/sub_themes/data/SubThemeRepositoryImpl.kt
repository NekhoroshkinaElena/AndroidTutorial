package com.example.androidtutorial2.sub_themes.data

import com.example.androidtutorial2.sub_themes.data.converter.subThemeEntityToSubTheme
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme
import com.example.androidtutorial2.sub_themes.domain.SubThemesRepository
import com.example.androidtutorial2.themes.data.db.TutorialDb
import javax.inject.Inject

class SubThemeRepositoryImpl @Inject constructor(private val db: TutorialDb) : SubThemesRepository {

    override suspend fun getAllSubThemes(): List<SubTheme> {
        return db.getSubThemeDao().getAllSubThemes().map {
            subThemeEntityToSubTheme(it)
        }
    }

    override suspend fun getSubThemeById(subThemeId: Int): SubTheme? {
        val subTheme = db.getSubThemeDao().getSubThemeById(subThemeId)
        return if (subTheme == null){
            null
        } else {
            subThemeEntityToSubTheme(subTheme)
        }
    }

    override suspend fun getSubThemes(themeId: Int): List<SubTheme> {
        return db.getSubThemeDao().getSubThemes(themeId).map {
            subThemeEntityToSubTheme(it)
        }
    }

    override suspend fun updateNumberRepetitions(subThemeId: Int, numberRepetitions: Int) {
        db.getSubThemeDao().updateNumberRepetitions(subThemeId, numberRepetitions)
    }

    override suspend fun updateSelectionState(subThemeId: Int, isSelected: Boolean) {
        db.getSubThemeDao().updateSelectionState(subThemeId, isSelected)
    }

    override suspend fun resetProgress(subThemeId: Int) {
        db.getSubThemeDao().resetProgress(subThemeId)
    }
}
