package com.example.androidtutorial2.topic.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    val id: Int,
    val name: String,
    val blocked: Boolean,
    val subTopicsCount: Int,
    val repeatedSubTopics: Int,
    val isTopicInStudy: Boolean,
    val isTopicCompleted: Boolean
) : Parcelable
