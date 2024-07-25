package com.example.androidtutorial2.themes.domain

import javax.inject.Inject

class ThemesInteractorImpl
@Inject constructor(private val themesRepository: ThemesRepository) :
    ThemesInteractor {

    override fun getListThemes(): List<String> {
        return themesRepository.getListThemes()
    }
}
