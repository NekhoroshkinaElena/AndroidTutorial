package com.example.androidtutorial2.sub_themes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubTheme(
    val id: Int,
    val name: String,
    val materialStudy: String,
    val numberRepetitions: Int,
    val isSelected: Boolean,
    val themeId: Int
) : Parcelable
