package com.example.androidtutorial2.topic.data

import com.example.androidtutorial2.topic.data.converter.topicEntityToTopic
import com.example.androidtutorial2.topic.data.db.TutorialDb
import com.example.androidtutorial2.topic.domain.TopicsRepository
import com.example.androidtutorial2.topic.domain.model.Topic
import javax.inject.Inject

class TopicsRepositoryImpl @Inject constructor(private val db: TutorialDb) : TopicsRepository {

    override suspend fun getListTopics(): List<Topic> {
        return db.getTopicDao().getAllTopics().map {
            topicEntityToTopic(
                it, db.getSubTopicDao().getSubTopics(it.id).size,
                0
            )
        }
    }
}
