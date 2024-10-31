package com.example.androidtutorial2.material_study.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import com.example.androidtutorial2.material_study.domain.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MaterialStudyViewModel @Inject constructor(
    private val materialStudyInteractor: MaterialStudyInteractor
) : BaseViewModel<QuestionsScreenState>(QuestionsScreenState.Loading) {

    private var listQuestions: List<Question> = emptyList()

    fun getListQuestions(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            listQuestions = materialStudyInteractor.getQuestions(subThemeId)
            withContext(Dispatchers.Main) {
                updateScreenState(QuestionsScreenState.Content(listQuestions))
            }
        }
    }
}
