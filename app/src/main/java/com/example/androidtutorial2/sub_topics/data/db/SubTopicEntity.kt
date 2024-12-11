package com.example.androidtutorial2.sub_topics.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.androidtutorial2.topic.data.db.TopicEntity

@Entity(
    tableName = "sub_themes",
    foreignKeys = [ForeignKey(
        entity = TopicEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("theme_id")
    )]
)
data class SubTopicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "material_of_study")
    val materialStudy: String,
    @ColumnInfo(name = "theme_id")
    val topicId: Int,
    @ColumnInfo(name = "number_repetitions")
    val numberRepetitions: Int?,
    @ColumnInfo(name = "is_selected")
    val isSelected: Int
)
