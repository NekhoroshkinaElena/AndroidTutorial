package com.example.androidtutorial2.material_study.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.R
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import com.example.androidtutorial2.resources.CssLoadException
import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StyleStringsProvider
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialStudyViewModel @Inject constructor(
    private val materialStudyInteractor: MaterialStudyInteractor,
    private val styleStringsProvider: StyleStringsProvider,
    private val subTopicsInteractor: SubTopicsInteractor,
    private val stringProvider: StringProvider
) : BaseViewModel<QuestionsScreenState>(QuestionsScreenState.Loading) {

    fun showMaterialStudy(subTopicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subTopic = subTopicsInteractor.getSubTopicById(subTopicId)
                if (subTopic != null) {
                    val questions = materialStudyInteractor.getQuestions(subTopicId)
                    val css = styleStringsProvider.loadStyleCss()
                    updateScreenState(
                        QuestionsScreenState.Content(
                            listQuestions = questions,
                            cssStyle = css,
                            subTopic = subTopic
                        )
                    )
                } else {
                    updateScreenState(
                        QuestionsScreenState.Error(
                            stringProvider.getString(R.string.error_sub_topic_not_found)
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
