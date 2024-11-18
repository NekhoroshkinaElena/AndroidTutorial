package com.example.androidtutorial2.sub_themes.data.converter

import com.example.androidtutorial2.sub_themes.data.db.SubThemeEntity
import com.example.androidtutorial2.sub_themes.domain.model.SubTheme

fun subThemeEntityToSubTheme(subThemeEntity: SubThemeEntity): SubTheme {
    return SubTheme(
        id = subThemeEntity.id,
        name = subThemeEntity.name,
        numberRepetitions = subThemeEntity.numberRepetitions ?: 0,
        materialStudy = subThemeEntity.materialStudy,
        isSelected = subThemeEntity.isSelected != 0,
        themeId = subThemeEntity.themeId
    )
}
