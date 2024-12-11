package com.example.androidtutorial2.sub_topics_repeat.ui

import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

sealed class SubTopicsRepeatScreenState {
    data object Loading : SubTopicsRepeatScreenState()
    data class Content(val subTopics: List<SubTopic>) : SubTopicsRepeatScreenState()
    data object Error : SubTopicsRepeatScreenState()
}
