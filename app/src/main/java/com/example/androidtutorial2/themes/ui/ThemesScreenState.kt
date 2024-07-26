package com.example.androidtutorial2.themes.ui

import com.example.androidtutorial2.themes.domain.model.Theme

sealed class ThemesScreenState {

    data object Loading : ThemesScreenState()
    data class Content(val listThemes: List<Theme>) : ThemesScreenState()
    data object Error : ThemesScreenState()
}
