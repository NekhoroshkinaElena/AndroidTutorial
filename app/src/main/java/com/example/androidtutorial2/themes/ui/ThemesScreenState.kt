package com.example.androidtutorial2.themes.ui

sealed class ThemesScreenState {

    data object Loading : ThemesScreenState()
    data class Content(val listThemes: List<String>) : ThemesScreenState()
    data object Error : ThemesScreenState()
}
