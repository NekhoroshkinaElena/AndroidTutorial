package com.example.androidtutorial2.topic.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopicDao {

    @Insert
    fun insertItem(topicEntity: TopicEntity)

    @Query("SELECT * FROM themes")
    suspend fun getAllTopics(): List<TopicEntity>
}
