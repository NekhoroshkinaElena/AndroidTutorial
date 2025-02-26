package com.example.androidtutorial2.material_study_repeat

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.R
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.resources.CssLoadException
import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StyleStringsProvider
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyRepeatViewModel @Inject constructor(
    private val subTopicsInteractor: SubTopicsInteractor,
    private val styleStringsProvider: StyleStringsProvider,
    private val stringsProvider: StringProvider
) : BaseViewModel<MaterialStudyRepeatScreenState>(MaterialStudyRepeatScreenState.Loading) {

    fun showMaterialStudy(subTopicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subTopic = subTopicsInteractor.getSubTopicById(subTopicId)
                if (subTopic != null) {
                    val css = styleStringsProvider.loadStyleCss()
                    updateScreenState(
                        MaterialStudyRepeatScreenState.Content(
                            subTopic = subTopic,
                            cssStyle = css
                        )
                    )
                } else {
                    updateScreenState(
                        MaterialStudyRepeatScreenState.Error(
                            stringsProvider.getString(
                                R.string.error_sub_topic_not_found
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

    fun updateNumberRepetitions(subTopicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTopic = subTopicsInteractor.getSubTopicById(subTopicId)
            if (subTopic != null) {
                subTopicsInteractor.updateNumberRepetitions(
                    subTopic.id,
                    subTopic.numberRepetitions + 1
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

    fun resetProgress(subTopicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subTopicsInteractor.resetProgress(subTopicId)
        }
    }

    fun postponeRepetition(subTopicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subTopicsInteractor.updateSelectionState(subTopicId, false)
        }
    }
}
