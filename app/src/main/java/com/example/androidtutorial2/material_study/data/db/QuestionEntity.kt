package com.example.androidtutorial2.material_study.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.androidtutorial2.sub_themes.data.db.SubThemeEntity

@Entity(
    tableName = "self_test_questions",
    foreignKeys = [ForeignKey(
        entity = SubThemeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sub_theme_id")
    )]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "answer")
    val answer: String,
    @ColumnInfo(name = "sub_theme_id")
    val subThemeId: Int
)
