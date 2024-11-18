package com.example.androidtutorial2.themes.domain

import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import com.example.androidtutorial2.themes.domain.model.Theme
import javax.inject.Inject

class ThemesInteractorImpl
@Inject constructor(
    private val themesRepository: ThemesRepository,
    private val subThemesInteractor: SubThemesInteractor
) :
    ThemesInteractor {

    override suspend fun getListThemes(): List<Theme> {
        return themesRepository.getListThemes().map { theme ->
            theme.copy(
                isThemeInStudy = isSubThemeSelected(theme.id),
                isThemeCompleted = areAllSubThemesCompleted(theme.id)
            )
        }
    }

    private suspend fun isSubThemeSelected(themeId: Int): Boolean {
        val subThemes = subThemesInteractor.getSubThemes(themeId)
        val isSelectedSubTheme: Boolean = subThemes.any { it.isSelected }
        return isSelectedSubTheme
    }

    private suspend fun areAllSubThemesCompleted(themeId: Int): Boolean {
        val subThemes = subThemesInteractor.getSubThemes(themeId)
        val completedSubThemesCount = subThemes.count { it.numberRepetitions >= 7 }
        return completedSubThemesCount == subThemes.size
    }
}
