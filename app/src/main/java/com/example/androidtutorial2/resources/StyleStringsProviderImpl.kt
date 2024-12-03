package com.example.androidtutorial2.resources

import android.content.Context
import java.io.IOException
import javax.inject.Inject

class StyleStringsProviderImpl @Inject constructor(private val context: Context) :
    StyleStringsProvider {

    override suspend fun loadStyleCss(): String {
        return try {
            context.assets.open("styles.css").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            throw CssLoadException("Ошибка загрузки CSS: ${e.message}")
        }
    }
}

class CssLoadException(message: String) : Exception(message)
