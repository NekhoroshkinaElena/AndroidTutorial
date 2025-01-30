package com.example.androidtutorial2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NotificationData(
    val topicId: Int,
    val topicName: String,
    val message: String,
    val currentRepetition: Int = 0,
) : Parcelable
