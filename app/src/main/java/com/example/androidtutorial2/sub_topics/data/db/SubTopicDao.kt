package com.example.androidtutorial2.sub_topics.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SubTopicDao {

    @Query("SELECT * FROM sub_themes")
    suspend fun getAllSubTopics(): List<SubTopicEntity>

    @Query("SELECT * FROM sub_themes WHERE id = :subTopicId")
    suspend fun getSubTopicById(subTopicId: Int): SubTopicEntity?

    @Query("SELECT * FROM sub_themes where theme_id = :topicId")
    suspend fun getSubTopics(topicId: Int): List<SubTopicEntity>

    @Query("UPDATE sub_themes SET number_repetitions = :numberRepetitions WHERE id = :id")
    suspend fun updateNumberRepetitions(id: Int, numberRepetitions: Int)

    @Query("UPDATE sub_themes SET is_selected = :isSelected WHERE id = :id")
    suspend fun updateSelectionState(id: Int, isSelected: Boolean)

    @Query(
        "UPDATE sub_themes SET is_selected = :isSelected, " +
                "number_repetitions = :numberRepetitions WHERE id = :subTopicId"
    )
    suspend fun resetProgress(subTopicId: Int, isSelected: Int = 0, numberRepetitions: Int = 0)
}
