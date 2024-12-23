package com.example.androidtutorial2.repeat.ui

import com.example.androidtutorial2.topic.domain.model.Topic


sealed class RepeatScreenState {

    data object Loading : RepeatScreenState()
    data class Content(val listTopics: List<Topic>) : RepeatScreenState()
    data object Error : RepeatScreenState()
}
