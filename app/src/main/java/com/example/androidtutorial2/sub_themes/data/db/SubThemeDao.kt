package com.example.androidtutorial2.sub_themes.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SubThemeDao {

    @Query("SELECT * FROM sub_themes")
    suspend fun getAllSubThemes(): List<SubThemeEntity>

    @Query("SELECT * FROM sub_themes where theme_id = :themeId")
    suspend fun getSubThemes(themeId: Int) : List<SubThemeEntity>
}
