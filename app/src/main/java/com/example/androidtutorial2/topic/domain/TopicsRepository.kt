package com.example.androidtutorial2.topic.domain

import com.example.androidtutorial2.topic.domain.model.Topic

interface TopicsRepository {
    suspend fun getListTopics(): List<Topic>
}
