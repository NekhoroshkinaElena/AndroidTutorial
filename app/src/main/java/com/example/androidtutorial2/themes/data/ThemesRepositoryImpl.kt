package com.example.androidtutorial2.themes.data

import com.example.androidtutorial2.themes.domain.ThemesRepository
import javax.inject.Inject

class ThemesRepositoryImpl @Inject constructor() : ThemesRepository {

    override fun getListThemes(): List<String> {
        return listOf("Kotlin", "Java", "ООП", "Фрагменты", "Ресурсы")
    }
}
