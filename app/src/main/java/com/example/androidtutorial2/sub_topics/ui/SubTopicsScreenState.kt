package com.example.androidtutorial2.sub_topics.ui

import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

sealed class SubTopicsScreenState {
    data object Loading : SubTopicsScreenState()
    data class Content(val listTopics: List<SubTopic>) : SubTopicsScreenState()
    data object Error : SubTopicsScreenState()
}
