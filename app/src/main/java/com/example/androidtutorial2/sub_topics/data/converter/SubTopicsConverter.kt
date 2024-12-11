package com.example.androidtutorial2.sub_topics.data.converter

import com.example.androidtutorial2.sub_topics.data.db.SubTopicEntity
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic

fun subTopicEntityToSubTopic(subTopicEntity: SubTopicEntity): SubTopic {
    return SubTopic(
        id = subTopicEntity.id,
        name = subTopicEntity.name,
        numberRepetitions = subTopicEntity.numberRepetitions ?: 0,
        materialStudy = subTopicEntity.materialStudy,
        isSelected = subTopicEntity.isSelected != 0,
        topicId = subTopicEntity.topicId
    )
}
