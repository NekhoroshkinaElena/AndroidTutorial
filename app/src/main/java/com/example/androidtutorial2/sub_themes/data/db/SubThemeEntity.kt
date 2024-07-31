package com.example.androidtutorial2.sub_themes.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.androidtutorial2.themes.data.db.ThemeEntity

@Entity(
    tableName = "sub_themes",
    foreignKeys = [ForeignKey(
        entity = ThemeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("theme_id")
    )]
)
data class SubThemeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "material_of_study")
    val materialStudy: String,
    @ColumnInfo(name = "theme_id")
    val themeId: Int
)
