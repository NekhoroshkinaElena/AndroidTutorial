package com.example.androidtutorial2.sub_topics.data

import com.example.androidtutorial2.sub_topics.data.converter.subTopicEntityToSubTopic
import com.example.androidtutorial2.sub_topics.domain.SubTopicsRepository
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic
import com.example.androidtutorial2.topic.data.db.TutorialDb
import javax.inject.Inject

class SubTopicsRepositoryImpl @Inject constructor(private val db: TutorialDb) :
    SubTopicsRepository {

    override suspend fun getAllSubTopics(): List<SubTopic> {
        return db.getSubTopicDao().getAllSubTopics().map {
            subTopicEntityToSubTopic(it)
        }
    }

    override suspend fun getSubTopicById(subTopicId: Int): SubTopic? {
        val subTopic = db.getSubTopicDao().getSubTopicById(subTopicId)
        return if (subTopic == null) {
            null
        } else {
            subTopicEntityToSubTopic(subTopic)
        }
    }

    override suspend fun getSubTopics(topicId: Int): List<SubTopic> {
        return db.getSubTopicDao().getSubTopics(topicId).map {
            subTopicEntityToSubTopic(it)
        }
    }

    override suspend fun updateNumberRepetitions(subTopicId: Int, numberRepetitions: Int) {
        db.getSubTopicDao().updateNumberRepetitions(subTopicId, numberRepetitions)
    }

    override suspend fun updateSelectionState(subTopicId: Int, isSelected: Boolean) {
        db.getSubTopicDao().updateSelectionState(subTopicId, isSelected)
    }

    override suspend fun resetProgress(subTopicId: Int) {
        db.getSubTopicDao().resetProgress(subTopicId)
    }
}
