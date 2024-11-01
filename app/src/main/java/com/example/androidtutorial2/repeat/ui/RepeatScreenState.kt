package com.example.androidtutorial2.repeat.ui

import com.example.androidtutorial2.themes.domain.model.Theme


sealed class RepeatScreenState {

    data object Loading : RepeatScreenState()
    data class Content(val listThemes: List<Theme>) : RepeatScreenState()
    data object Error : RepeatScreenState()
}
