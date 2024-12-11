package com.example.androidtutorial2.sub_topics.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubTopicsViewModel @Inject constructor(
    private val subTopicsInteractor: SubTopicsInteractor
) : BaseViewModel<SubTopicsScreenState>(SubTopicsScreenState.Loading) {

    fun getSubTopics(topicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTopics = subTopicsInteractor.getSubTopics(topicId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubTopicsScreenState.Content(subTopics))
            }
        }
    }
}
