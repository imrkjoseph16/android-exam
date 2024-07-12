package com.imrkjoseph.cybillteckexam.persons.presentation.details

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.imrkjoseph.cybillteckexam.R
import com.imrkjoseph.cybillteckexam.app.component.TextLine
import com.imrkjoseph.cybillteckexam.app.shared.dto.AvatarItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.cybillteckexam.app.util.Default.Companion.MONTH_DAY_YEAR
import com.imrkjoseph.cybillteckexam.app.util.Default.Companion.YEAR_DAY_MONTH_TIMEZONE
import com.imrkjoseph.cybillteckexam.app.util.ViewUtil
import com.imrkjoseph.cybillteckexam.persons.data.dto.Location
import com.imrkjoseph.cybillteckexam.persons.data.dto.Result
import javax.inject.Inject

class PersonDetailsFactory @Inject constructor(
    private val viewUtil: ViewUtil
) {

    fun createOverview(details: Result?) = buildList {
        // Title
        add(element = SpaceItemViewDto(R.dimen.distance_24x))
        add(element = TitleItemViewDto(title = TextLine(textRes = R.string.title_person_details), textSize = 28F))

        details?.let { result ->
            with(result) {
                // Avatar
                add(element = SpaceItemViewDto(R.dimen.distance_24x))
                add(element = AvatarItemViewDto(avatarUrl = picture.large))

                // First name
                setupTitleItem(
                    text = "${name.title}. ${name.first}",
                    textRes = R.string.title_first_name,
                    spaceHeight = R.dimen.distance_30x
                )

                // Last name
                setupTitleItem(text = name.last, textRes = R.string.title_last_name)

                // Birthday
                setupTitleItem(text = viewUtil.convertStringDate(
                    formatDate = YEAR_DAY_MONTH_TIMEZONE,
                    desiredFormat = MONTH_DAY_YEAR,
                    inputDate = dob.date
                ), textRes = R.string.title_birthday)

                // Age
                setupTitleItem(text = dob.age.toString(), textRes = R.string.title_age)

                // Email Address
                setupTitleItem(text = email, textRes = R.string.title_email)

                // Mobile Number
                setupTitleItem(text = cell, textRes = R.string.title_mobile_number)

                // Home Address
                setupTitleItem(text = location.buildStreetAddress(), textRes = R.string.title_address)
            }
        }
    }

    private fun Location.buildStreetAddress() = "${street.number} ${street.name}, $state $city, $country"

    private fun MutableList<Any>.setupTitleItem(
        text: String,
        @StringRes textRes: Int,
        @DimenRes spaceHeight: Int = R.dimen.distance_8x
    ) {
        add(element = SpaceItemViewDto(spaceHeight))
        add(element = TitleItemViewDto(title = TextLine(
            textRes = textRes,
            text = text)
        ))
    }
}