package com.example.androidtutorial2.material_study_repeat

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyRepeatViewModel @Inject constructor(
    private val subThemesInteractor: SubThemesInteractor
) : BaseViewModel<MaterialStudyRepeatScreenState>(MaterialStudyRepeatScreenState.Loading) {

    fun showMaterialStudy(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTheme = subThemesInteractor.getSubThemeById(subThemeId)
            if (subTheme != null) {
                updateScreenState(MaterialStudyRepeatScreenState.Content(subTheme))
            } else {
                updateScreenState(MaterialStudyRepeatScreenState.Error)
            }
        }
    }

    fun updateNumberRepetitions(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTheme = subThemesInteractor.getSubThemeById(subThemeId)
            if (subTheme != null) {
                subThemesInteractor.updateNumberRepetitions(
                    subTheme.id,
                    subTheme.numberRepetitions + 1
                )
            } else {
                updateScreenState(MaterialStudyRepeatScreenState.Error)
            }
        }
    }
}
