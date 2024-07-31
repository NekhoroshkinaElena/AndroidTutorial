package com.example.androidtutorial2.utils

import android.text.Editable
import android.text.Html
import org.xml.sax.XMLReader

class TagHandler : Html.TagHandler {
    override fun handleTag(
        opening: Boolean, tag: String,
        output: Editable, xmlReader: XMLReader
    ) {
        if (!opening && tag == "ul") {
            output.append("\n")
        }
        if (opening && tag == "iff") {
            output.append("\n\u2022 hgjhgjhg")
        }
        if (opening && tag == "p") {
            output.append("\t")
        }
    }
}
