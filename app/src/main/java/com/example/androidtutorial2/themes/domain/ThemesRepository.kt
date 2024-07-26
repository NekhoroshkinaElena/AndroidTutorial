package com.example.androidtutorial2.themes.domain

import com.example.androidtutorial2.themes.domain.model.Theme

interface ThemesRepository {
    fun getListThemes(): List<Theme>
}
