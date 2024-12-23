package com.example.androidtutorial2.topic.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.topic.domain.TopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicsViewModel @Inject constructor(private val topicsInteractor: TopicsInteractor) :
    BaseViewModel<TopicsScreenState>(TopicsScreenState.Loading) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val topics = topicsInteractor.getListTopics()

            withContext(Dispatchers.Main) {
                updateScreenState(TopicsScreenState.Content(topics))
            }
        }
    }
}
