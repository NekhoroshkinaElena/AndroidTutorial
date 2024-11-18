package com.example.androidtutorial2.themes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val id: Int,
    val name: String,
    val blocked: Boolean,
    val subThemesCount: Int,
    val repeatedSubThemes: Int,
    val isThemeInStudy: Boolean,
    val isThemeCompleted: Boolean
) : Parcelable
