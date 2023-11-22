package com.macaosoftware.sdui.app.view.panel.date

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
 class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.replace("[^\\d]".toRegex(), "")
        val trimmedLength = trimmed.length.coerceAtMost(4)
        val transformed = buildAnnotatedString {
            when (trimmedLength) {
                in 1..2 -> {
                    append(trimmed.substring(0 until trimmedLength))
                }
                3 -> {
                    append(trimmed.substring(0 until 2))
                    append("/")
                    append(trimmed.substring(2))
                }
                4 -> {
                    append(trimmed.substring(0 until 2))
                    append("/")
                    append(trimmed.substring(2 until 4))
                }
                else -> {
                    // Do nothing, invalid date format
                }
            }
        }
        return TransformedText(transformed, OffsetMapping.Identity)
    }
}