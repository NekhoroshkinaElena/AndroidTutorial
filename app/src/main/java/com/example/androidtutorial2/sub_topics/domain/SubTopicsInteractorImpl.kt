package com.example.androidtutorial2.sub_topics.domain

import com.example.androidtutorial2.sub_topics.domain.model.SubTopic
import javax.inject.Inject

class SubTopicsInteractorImpl @Inject constructor(
    private val subTopicsRepository: SubTopicsRepository
) : SubTopicsInteractor {

    override suspend fun getAllSubTopics(): List<SubTopic> {
        return subTopicsRepository.getAllSubTopics()
    }

    override suspend fun getSubTopicById(subTopicId: Int): SubTopic? {
        return subTopicsRepository.getSubTopicById(subTopicId)
    }

    override suspend fun getSubTopics(topicId: Int): List<SubTopic> {
        return subTopicsRepository.getSubTopics(topicId)
    }

    override suspend fun updateNumberRepetitions(subTopicId: Int, numberRepetitions: Int) {
        subTopicsRepository.updateNumberRepetitions(subTopicId, numberRepetitions)
    }

    override suspend fun updateSelectionState(subTopicId: Int, isSelected: Boolean) {
        subTopicsRepository.updateSelectionState(subTopicId, isSelected)
    }

    override suspend fun resetProgress(subTopicId: Int) {
        subTopicsRepository.resetProgress(subTopicId)
    }
}
