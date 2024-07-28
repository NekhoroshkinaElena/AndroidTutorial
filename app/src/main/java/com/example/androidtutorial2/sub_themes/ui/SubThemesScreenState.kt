package com.example.androidtutorial2.sub_themes.ui

import com.example.androidtutorial2.sub_themes.domain.SubTheme

sealed class SubThemesScreenState {
    data object Loading : SubThemesScreenState()
    data class Content(val listThemes: List<SubTheme>) : SubThemesScreenState()
    data object Error : SubThemesScreenState()
}
