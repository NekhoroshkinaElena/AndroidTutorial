package com.example.androidtutorial2.material_study.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyViewModel @Inject constructor(
    private val materialStudyInteractor: MaterialStudyInteractor
) : ViewModel() {

    fun get() {
        viewModelScope.launch {
            Log.i("TAG2", ": ${materialStudyInteractor.getQuestions(5)}")
        }
    }
}
