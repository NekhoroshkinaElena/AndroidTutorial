package com.example.androidtutorial2.themes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ThemeDao {

    @Insert
    fun insertItem(themeEntity: ThemeEntity)

    @Query("SELECT * FROM themes")
    suspend fun getAllThemes(): List<ThemeEntity>
}
