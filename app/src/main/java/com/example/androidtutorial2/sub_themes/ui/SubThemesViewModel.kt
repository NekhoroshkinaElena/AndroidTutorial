package com.example.androidtutorial2.sub_themes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubThemesViewModel @Inject constructor(
    private val subThemesInteractor: SubThemesInteractor
) : ViewModel() {

    private val _screenState = MutableLiveData<SubThemesScreenState>(SubThemesScreenState.Loading)
    val screenState: LiveData<SubThemesScreenState> = _screenState

    fun getSubThemes(themeId: Int){
        viewModelScope.launch {
            _screenState.postValue(
                SubThemesScreenState
                    .Content(subThemesInteractor.getSubThemes(themeId))
            )
        }
    }
}
