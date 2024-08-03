package com.example.androidtutorial2.themes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtutorial2.material_study.data.db.QuestionEntity
import com.example.androidtutorial2.material_study.data.db.QuestionsDao
import com.example.androidtutorial2.sub_themes.data.db.SubThemeDao
import com.example.androidtutorial2.sub_themes.data.db.SubThemeEntity

@Database(
    entities = [ThemeEntity::class, SubThemeEntity::class, QuestionEntity::class],
    version = 1
)
abstract class TutorialDb : RoomDatabase() {

    abstract fun getThemeDao(): ThemeDao

    abstract fun getSubThemeDao(): SubThemeDao

    abstract fun getQuestions(): QuestionsDao
}
