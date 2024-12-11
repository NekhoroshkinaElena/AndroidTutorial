package com.example.androidtutorial2.topic.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtutorial2.material_study.data.db.QuestionEntity
import com.example.androidtutorial2.material_study.data.db.QuestionsDao
import com.example.androidtutorial2.sub_topics.data.db.SubTopicDao
import com.example.androidtutorial2.sub_topics.data.db.SubTopicEntity

@Database(
    entities = [TopicEntity::class, SubTopicEntity::class, QuestionEntity::class],
    version = 1
)
abstract class TutorialDb : RoomDatabase() {

    abstract fun getTopicDao(): TopicDao

    abstract fun getSubTopicDao(): SubTopicDao

    abstract fun getQuestions(): QuestionsDao
}
