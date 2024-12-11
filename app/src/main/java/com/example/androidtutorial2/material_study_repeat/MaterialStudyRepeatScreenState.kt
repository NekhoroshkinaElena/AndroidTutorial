package com.example.androidtutorial2.material_study_repeat

import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

sealed class MaterialStudyRepeatScreenState {
    data class Content(val subTopic: SubTopic, val cssStyle: String) :
        MaterialStudyRepeatScreenState()

    data object Loading : MaterialStudyRepeatScreenState()
    data class Error(val message: String) : MaterialStudyRepeatScreenState()
}
