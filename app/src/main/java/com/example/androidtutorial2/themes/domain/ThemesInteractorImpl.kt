package com.example.androidtutorial2.themes.domain

import com.example.androidtutorial2.themes.domain.model.Theme
import javax.inject.Inject

class ThemesInteractorImpl
@Inject constructor(private val themesRepository: ThemesRepository) :
    ThemesInteractor {

    override suspend fun getListThemes(): List<Theme> {
        return themesRepository.getListThemes()
    }
}
