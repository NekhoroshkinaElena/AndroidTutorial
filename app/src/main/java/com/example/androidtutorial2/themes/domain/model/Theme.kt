package com.example.androidtutorial2.themes.domain.model

import android.os.Parcelable
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val id: Long,
    val name: String,
    val description: String,
    val subThemes: List<SubTheme>?
) : Parcelable
