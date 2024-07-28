package com.example.androidtutorial2.themes.domain.model

import android.os.Parcelable
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val id: Int,
    val name: String
) : Parcelable
