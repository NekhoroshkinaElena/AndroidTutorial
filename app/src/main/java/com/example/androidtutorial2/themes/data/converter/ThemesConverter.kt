package com.example.androidtutorial2.themes.data.converter

import com.example.androidtutorial2.themes.data.db.ThemeEntity
import com.example.androidtutorial2.themes.domain.model.Theme


fun themeEntityToTheme(
    themeEntity: ThemeEntity,
    subThemesCount: Int,
    repeatedSubThemes: Int
): Theme {
    return Theme(
        themeEntity.id,
        themeEntity.name,
        themeEntity.blocked != 0,
        subThemesCount,
        repeatedSubThemes
    )
}
