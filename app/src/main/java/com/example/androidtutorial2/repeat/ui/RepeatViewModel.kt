package com.example.androidtutorial2.repeat.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.topic.domain.TopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepeatViewModel @Inject constructor(private val topicsInteractor: TopicsInteractor) :
    BaseViewModel<RepeatScreenState>(RepeatScreenState.Loading) {

    init {
        loadTopics()
    }

    fun loadTopics() {
        viewModelScope.launch(Dispatchers.IO) {
            val topics = topicsInteractor.getListTopics()
            withContext(Dispatchers.Main) {
                updateScreenState(RepeatScreenState.Content(topics))
            }
        }
    }
}
