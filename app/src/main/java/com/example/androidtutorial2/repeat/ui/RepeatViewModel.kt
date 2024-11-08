package com.example.androidtutorial2.repeat.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.themes.domain.ThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepeatViewModel @Inject constructor(private val themesInteractor: ThemesInteractor) :
    BaseViewModel<RepeatScreenState>(RepeatScreenState.Loading) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = themesInteractor.getListThemes()

            withContext(Dispatchers.Main) {
                updateScreenState(RepeatScreenState.Content(themes))
            }
        }
    }
}
