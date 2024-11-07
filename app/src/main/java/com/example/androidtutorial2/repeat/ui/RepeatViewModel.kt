package com.example.androidtutorial2.repeat.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.themes.domain.ThemesInteractor
import com.example.androidtutorial2.themes.domain.model.Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepeatViewModel @Inject constructor(private val themesInteractor: ThemesInteractor) :
    BaseViewModel<RepeatScreenState>(RepeatScreenState.Loading) {

    private var listTheme: List<Theme> = emptyList()
    private val themesMutex = Mutex()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = themesInteractor.getListThemes()

            themesMutex.withLock {
                listTheme = themes
            }

            withContext(Dispatchers.Main) {
                updateScreenState(RepeatScreenState.Content(listTheme))
            }
        }
    }
}
