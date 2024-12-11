package com.example.androidtutorial2.sub_topics.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubTopic(
    val id: Int,
    val name: String,
    val materialStudy: String,
    val numberRepetitions: Int,
    val isSelected: Boolean,
    val topicId: Int
) : Parcelable
