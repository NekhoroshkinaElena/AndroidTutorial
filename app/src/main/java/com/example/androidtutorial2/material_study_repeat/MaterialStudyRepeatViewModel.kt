package com.example.androidtutorial2.material_study_repeat

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.R
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.resources.CssLoadException
import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StyleStringsProvider
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyRepeatViewModel @Inject constructor(
    private val subThemesInteractor: SubThemesInteractor,
    private val styleStringsProvider: StyleStringsProvider,
    private val stringsProvider: StringProvider
) : BaseViewModel<MaterialStudyRepeatScreenState>(MaterialStudyRepeatScreenState.Loading) {

    fun showMaterialStudy(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subTheme = subThemesInteractor.getSubThemeById(subThemeId)
                if (subTheme != null) {
                    val css = styleStringsProvider.loadStyleCss()
                    updateScreenState(
                        MaterialStudyRepeatScreenState.Content(
                            subTheme = subTheme,
                            cssStyle = css
                        )
                    )
                } else {
                    updateScreenState(
                        MaterialStudyRepeatScreenState.Error(
                            stringsProvider.getString(
                                R.string.error_subtheme_not_found
                            )
                        )
                    )
                }
            } catch (e: CssLoadException) {
                updateScreenState(
                    MaterialStudyRepeatScreenState.Error(
                        stringsProvider.getString(
                            R.string.error_css_load
                        ) + ": ${e.message}"
                    )
                )
            } catch (e: Exception) {
                updateScreenState(
                    MaterialStudyRepeatScreenState.Error(
                        stringsProvider.getString(
                            R.string.error_unknown
                        )
                    )
                )
            }
        }
    }

    fun updateNumberRepetitions(subThemeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTheme = subThemesInteractor.getSubThemeById(subThemeId)
            if (subTheme != null) {
                subThemesInteractor.updateNumberRepetitions(
                    subTheme.id,
                    subTheme.numberRepetitions + 1
                )
            } else {
                updateScreenState(
                    MaterialStudyRepeatScreenState.Error(
                        stringsProvider.getString(R.string.error)
                    )
                )
            }
        }
    }
}
