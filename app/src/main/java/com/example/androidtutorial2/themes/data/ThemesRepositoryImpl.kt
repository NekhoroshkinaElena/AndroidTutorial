package com.example.androidtutorial2.themes.data

import com.example.androidtutorial2.themes.data.db.TutorialDb
import com.example.androidtutorial2.themes.domain.ThemesRepository
import com.example.androidtutorial2.themes.domain.model.Theme
import javax.inject.Inject

class ThemesRepositoryImpl @Inject constructor(private val db: TutorialDb) : ThemesRepository {

    override suspend fun getListThemes(): List<Theme> {
        return db.getThemeDao().getAllThemes().map {
            Theme(it.id, it.name, it.blocked != 0)
        }
    }
}
