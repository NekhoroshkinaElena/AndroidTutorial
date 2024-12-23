package com.example.androidtutorial2.sub_topics_repeat.ui

import androidx.lifecycle.viewModelScope
import com.example.androidtutorial2.base.BaseViewModel
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubTopicsRepeatViewModel @Inject constructor(
    private val subTopicsInteractor: SubTopicsInteractor
) : BaseViewModel<SubTopicsRepeatScreenState>(SubTopicsRepeatScreenState.Loading) {

    fun getSubTopics(topicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subTopics = subTopicsInteractor.getSubTopics(topicId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubTopicsRepeatScreenState.Content(subTopics))
            }
        }
    }

    fun updateSelectionState(subTopicId: Int, isSelected: Boolean, topicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subTopicsInteractor.updateSelectionState(subTopicId, isSelected)
            val subTopics = subTopicsInteractor.getSubTopics(topicId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubTopicsRepeatScreenState.Content(subTopics))
            }
        }
    }

    fun resetProgress(subTopicId: Int, topicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subTopicsInteractor.resetProgress(subTopicId)
            val subTopics = subTopicsInteractor.getSubTopics(topicId)

            withContext(Dispatchers.Main) {
                updateScreenState(SubTopicsRepeatScreenState.Content(subTopics))
            }
        }
    }
}
