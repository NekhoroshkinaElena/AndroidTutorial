package com.example.androidtutorial2.sub_topics.domain

import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

interface SubTopicsInteractor {

    suspend fun getAllSubTopics(): List<SubTopic>

    suspend fun getSubTopicById(subTopicId: Int): SubTopic?

    suspend fun getSubTopics(topicId: Int): List<SubTopic>

    suspend fun updateNumberRepetitions(subTopicId: Int, numberRepetitions: Int)

    suspend fun updateSelectionState(subTopicId: Int, isSelected: Boolean)

    suspend fun resetProgress(subTopicId: Int)
}
