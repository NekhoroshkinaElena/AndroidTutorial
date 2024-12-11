package com.example.androidtutorial2.topic.domain

import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import com.example.androidtutorial2.topic.domain.model.Topic
import javax.inject.Inject

class TopicsInteractorImpl
@Inject constructor(
    private val topicsRepository: TopicsRepository,
    private val subTopicsInteractor: SubTopicsInteractor
) :
    TopicsInteractor {

    override suspend fun getListTopics(): List<Topic> {
        return topicsRepository.getListTopics().map { topic ->
            topic.copy(
                isTopicInStudy = isSubTopicSelected(topic.id),
                isTopicCompleted = areAllSubTopicsCompleted(topic.id)
            )
        }
    }

    private suspend fun isSubTopicSelected(topicId: Int): Boolean {
        val subTopics = subTopicsInteractor.getSubTopics(topicId)
        val isSelectedSubTopics: Boolean = subTopics.any { it.isSelected }
        return isSelectedSubTopics
    }

    private suspend fun areAllSubTopicsCompleted(topicId: Int): Boolean {
        val subTopics = subTopicsInteractor.getSubTopics(topicId)
        val completedSubTopicsCount = subTopics.count { it.numberRepetitions >= 7 }
        return completedSubTopicsCount == subTopics.size
    }
}
