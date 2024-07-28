package com.example.androidtutorial2.themes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.themes.domain.ThemesInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThemesViewModel @Inject constructor(private val themesInteractor: ThemesInteractor) :
    ViewModel() {

    private val _screenState = MutableLiveData<ThemesScreenState>(ThemesScreenState.Loading)
    val screenState: LiveData<ThemesScreenState> = _screenState

    init {
        viewModelScope.launch {
            _screenState.postValue(ThemesScreenState.Content(themesInteractor.getListThemes()))
        }
    }
}
