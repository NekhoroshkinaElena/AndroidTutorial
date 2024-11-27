package com.example.androidtutorial2.styles

import android.content.Context
import java.io.IOException

class StyleStringsProviderImpl(private val context: Context) : StyleStringsProvider {

    override suspend fun loadStyleCss(): String {
        return try {
            context.assets.open("styles.css").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            ""
        }
    }
}
