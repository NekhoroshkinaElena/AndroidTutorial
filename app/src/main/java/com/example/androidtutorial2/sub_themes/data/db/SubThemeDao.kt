package com.example.androidtutorial2.sub_themes.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SubThemeDao {

    @Query("SELECT * FROM sub_themes")
    suspend fun getAllSubThemes(): List<SubThemeEntity>

    @Query("SELECT * FROM sub_themes WHERE id = :subThemeId")
    suspend fun getSubThemeById(subThemeId: Int): SubThemeEntity?

    @Query("SELECT * FROM sub_themes where theme_id = :themeId")
    suspend fun getSubThemes(themeId: Int): List<SubThemeEntity>

    @Query("UPDATE sub_themes SET number_repetitions = :numberRepetitions WHERE id = :id")
    suspend fun updateNumberRepetitions(id: Int, numberRepetitions: Int)

    @Query("UPDATE sub_themes SET is_selected = :isSelected WHERE id = :id")
    suspend fun updateSelectionState(id: Int, isSelected: Boolean)

    @Query("UPDATE sub_themes SET is_selected = :isSelected, number_repetitions = :numberRepetitions WHERE id = :subThemeId")
    suspend fun resetProgress(subThemeId: Int, isSelected: Int = 0, numberRepetitions: Int = 0)
}
