package com.imrkjoseph.cybillteckexam.app.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
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

    fun calculateAge(
        birthDate: String?,
        formatDate: String
    ) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        birthDate?.let {
            // Parse the date of birth from the formatDate.
            val dateOfBirth = LocalDate.parse(
                birthDate,
                DateTimeFormatter.ofPattern(formatDate)
            )
            // Then calculate the age in years at date of execution
            Period.between(dateOfBirth, LocalDate.now()).years
        }
    } else {
        0
    }
}