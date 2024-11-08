package com.example.androidtutorial2.sub_themes.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubThemesViewModel @Inject constructor(
    private val subThemesInteractor: SubThemesInteractor
) : BaseViewModel<SubThemesScreenState>(SubThemesScreenState.Loading) {

    fun getSubThemes(themeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subThemes = subThemesInteractor.getSubThemes(themeId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubThemesScreenState.Content(subThemes))
            }
        }
    }
}
