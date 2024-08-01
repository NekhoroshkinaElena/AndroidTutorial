package com.example.androidtutorial2.sub_themes.data

import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.sub_themes.domain.SubThemesRepository
import com.example.androidtutorial2.themes.data.db.TutorialDb
import javax.inject.Inject

class SubThemeRepositoryImpl @Inject constructor(private val db: TutorialDb) : SubThemesRepository {

    override suspend fun getAllSubThemes(): List<SubTheme> {
        return db.getSubThemeDao().getAllSubThemes().map {
            SubTheme(it.name, "")
        }
    }

    override suspend fun getSubThemes(themeId: Int): List<SubTheme> {
        return db.getSubThemeDao().getSubThemes(themeId).map {
            SubTheme(it.name, it.materialStudy)
        }
    }
}
