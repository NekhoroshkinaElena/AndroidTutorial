package com.example.androidtutorial2.material_study.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyViewModel @Inject constructor(
    private val materialStudyInteractor: MaterialStudyInteractor
) : ViewModel() {

    private val _screenState = MutableLiveData<QuestionsScreenState>(QuestionsScreenState.Loading)
    val screenState: LiveData<QuestionsScreenState> = _screenState

    fun get() {
        viewModelScope.launch {
            _screenState.postValue(
                QuestionsScreenState.Content(
                    materialStudyInteractor.getQuestions(
                        5
                    )
                )
            )
        }
    }
}
