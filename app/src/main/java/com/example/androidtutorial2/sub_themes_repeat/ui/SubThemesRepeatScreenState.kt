package com.example.androidtutorial2.sub_themes_repeat.ui

import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

sealed class SubThemesRepeatScreenState {
    data object Loading : SubThemesRepeatScreenState()
    data class Content(val listThemes: List<SubTheme>) : SubThemesRepeatScreenState()
    data object Error : SubThemesRepeatScreenState()
}
