package com.example.androidtutorial2.themes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val id: Int,
    val name: String,
    val blocked: Boolean
) : Parcelable
