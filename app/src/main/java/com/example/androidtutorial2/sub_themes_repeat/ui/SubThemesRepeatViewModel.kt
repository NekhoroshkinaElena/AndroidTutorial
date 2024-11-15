package com.example.androidtutorial2.sub_themes_repeat.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubThemesRepeatViewModel @Inject constructor(
    private val subThemesInteractor: SubThemesInteractor
) : BaseViewModel<SubThemesRepeatScreenState>(SubThemesRepeatScreenState.Loading) {

    fun getSubThemes(themeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subThemes = subThemesInteractor.getSubThemes(themeId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubThemesRepeatScreenState.Content(subThemes))
            }
        }
    }

    fun updateSelectionState(subThemeId: Int, isSelected: Boolean, themeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subThemesInteractor.updateSelectionState(subThemeId, isSelected)
            val subThemes = subThemesInteractor.getSubThemes(themeId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubThemesRepeatScreenState.Content(subThemes))
            }
        }
    }

    fun resetProgress(subThemeId: Int, themeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subThemesInteractor.resetProgress(subThemeId)
            val subThemes = subThemesInteractor.getSubThemes(themeId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubThemesRepeatScreenState.Content(subThemes))
            }
        }
    }
}
