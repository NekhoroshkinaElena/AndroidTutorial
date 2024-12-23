package com.example.androidtutorial2.topic.data.converter

import com.example.androidtutorial2.topic.data.db.TopicEntity
import com.example.androidtutorial2.topic.domain.model.Topic


fun topicEntityToTopic(
    topicEntity: TopicEntity,
    subTopicsCount: Int,
    repeatedSubTopics: Int
): Topic {
    return Topic(
        id = topicEntity.id,
        name = topicEntity.name,
        blocked = topicEntity.blocked != 0,
        subTopicsCount = subTopicsCount,
        repeatedSubTopics = repeatedSubTopics,
        isTopicInStudy = false,
        isTopicCompleted = false
    )
}
