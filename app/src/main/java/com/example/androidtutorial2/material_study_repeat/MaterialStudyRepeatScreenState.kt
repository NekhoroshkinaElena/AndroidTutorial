package com.example.androidtutorial2.material_study_repeat

import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

sealed class MaterialStudyRepeatScreenState {
    data class Content(val subTheme: SubTheme, val cssStyle: String) : MaterialStudyRepeatScreenState()
    data object Loading : MaterialStudyRepeatScreenState()
    data object Error : MaterialStudyRepeatScreenState()
}
