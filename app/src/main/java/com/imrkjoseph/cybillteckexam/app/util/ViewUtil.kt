package com.imrkjoseph.cybillteckexam.app.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class ViewUtil @Inject constructor() {

    @SuppressLint("SimpleDateFormat")
    fun convertStringDate(
        formatDate: String,
        desiredFormat: String,
        inputDate: String?
    ) = inputDate?.let {
        val convertedDate: Date
        val dateFormat = SimpleDateFormat(formatDate)
        return try {
            convertedDate = dateFormat.parse(it) as Date
            val dfOutput = SimpleDateFormat(desiredFormat)
            dfOutput.format(convertedDate)
        } catch (ignored: ParseException) {
            it
        }
    } ?: " "
}