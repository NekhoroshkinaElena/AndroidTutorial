package com.example.androidtutorial2.topic.ui

import com.example.androidtutorial2.topic.domain.model.Topic

sealed class TopicsScreenState {

    data object Loading : TopicsScreenState()
    data class Content(val listTopics: List<Topic>) : TopicsScreenState()
    data object Error : TopicsScreenState()
}
