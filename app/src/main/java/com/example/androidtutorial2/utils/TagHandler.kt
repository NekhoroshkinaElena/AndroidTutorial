package com.example.androidtutorial2.utils

import android.text.Editable
import android.text.Html
import android.util.Log
import org.xml.sax.XMLReader

class TagHandler : Html.TagHandler {
    override fun handleTag(
        opening: Boolean, tag: String,
        output: Editable, xmlReader: XMLReader
    ) {
//        if (!opening && tag == "p") {
//            Log.i("TAG2", "handleTag: !opening && tag")
//            output.append("\t")
//        }
        if (opening && tag == "iff") {
            output.append("\n\u2022 hgjhgjhg")
        }
        if (opening && tag == "z") {
            Log.i("TAG2", "handleTag: opening && tag")
            output.append("\t\t")
        }
    }
}
