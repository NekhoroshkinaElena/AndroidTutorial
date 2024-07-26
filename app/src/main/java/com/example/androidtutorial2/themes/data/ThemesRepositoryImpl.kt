package com.example.androidtutorial2.themes.data

import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.themes.domain.ThemesRepository
import com.example.androidtutorial2.themes.domain.model.Theme
import javax.inject.Inject

class ThemesRepositoryImpl @Inject constructor() : ThemesRepository {

    val list: List<SubTheme>
        get() = listOf(
            SubTheme("subtheme 1"),
            SubTheme("subtheme 2"),
            SubTheme("subtheme 3"),
            SubTheme("subtheme 4"),
            SubTheme("subtheme 5"),
            SubTheme("subtheme 6")
        )

    override fun getListThemes(): List<Theme> {
        return listOf(
            Theme(1, "Kotlin", "description Kotlin", list),
            Theme(2, "Java", "description Java", list),
            Theme(3, "ООП", "description OOP", list),
            Theme(4, "Фрагменты", "description fragments", list),
            Theme(5, "Ресурсы", "description Resources", list)
        )
    }
}
