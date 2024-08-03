package com.example.androidtutorial2.material_study.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM self_test_questions where sub_theme_id = :subThemeId")
    suspend fun getQuestions(subThemeId: Int): List<QuestionEntity>
}
