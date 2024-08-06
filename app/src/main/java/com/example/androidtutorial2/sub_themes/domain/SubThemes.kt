package com.example.androidtutorial2.sub_themes.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubTheme(
    val id: Int,
    val name: String,
    val materialStudy: String
) : Parcelable
