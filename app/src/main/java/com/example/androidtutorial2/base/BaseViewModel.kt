package com.example.androidtutorial2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State : Any>(initialState: State) : ViewModel() {

    private val _screenState: MutableLiveData<State> = MutableLiveData(initialState)
    val screenState: LiveData<State> = _screenState

    protected fun updateScreenState(newState: State) {
        _screenState.postValue(newState)
    }
}
