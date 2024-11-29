package com.example.androidtutorial2.material_study.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.R
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import com.example.androidtutorial2.resources.CssLoadException
import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StyleStringsProvider
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyViewModel @Inject constructor(
    private val materialStudyInteractor: MaterialStudyInteractor,
    private val styleStringsProvider: StyleStringsProvider,
    private val subThemesInteractor: SubThemesInteractor,
    private val stringProvider: StringProvider
) : BaseViewModel<QuestionsScreenState>(QuestionsScreenState.Loading) {

    fun showMaterialStudy(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subTheme = subThemesInteractor.getSubThemeById(subThemeId)
                if (subTheme != null) {
                    val questions = materialStudyInteractor.getQuestions(subThemeId)
                    val css = styleStringsProvider.loadStyleCss()
                    updateScreenState(
                        QuestionsScreenState.Content(
                            listQuestions = questions,
                            cssStyle = css,
                            subTheme = subTheme
                        )
                    )
                } else {
                    updateScreenState(
                        QuestionsScreenState.Error(
                            stringProvider.getString(R.string.error_subtheme_not_found)
                        )
                    )
                }
            } catch (e: CssLoadException) {
                updateScreenState(
                    QuestionsScreenState.Error(
                        stringProvider.getString(R.string.error_css_load) + ": ${e.message}"
                    )
                )
            } catch (e: Exception) {
                updateScreenState(
                    QuestionsScreenState.Error(
                        stringProvider.getString(R.string.error_unknown)
                    )
                )
            }
        }
    }
}
