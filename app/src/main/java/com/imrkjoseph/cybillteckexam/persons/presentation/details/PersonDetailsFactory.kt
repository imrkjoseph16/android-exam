package com.imrkjoseph.cybillteckexam.persons.presentation.details

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.imrkjoseph.cybillteckexam.R
import com.imrkjoseph.cybillteckexam.app.component.TextLine
import com.imrkjoseph.cybillteckexam.app.shared.dto.AvatarItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.ToolbarItemViewDto
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
        // Toolbar
        add(element = ToolbarItemViewDto(title = TextLine(textRes = R.string.title_person_details), titleSize = 24F))

        // Title
        details?.let { result ->
            with(result) {
                // Avatar
                add(element = SpaceItemViewDto(R.dimen.distance_30x))
                add(element = AvatarItemViewDto(avatarUrl = picture?.large))

                // Full name
                setupTitleItem(
                    textRes = R.string.title_full_name,
                    text = "${name?.title}. ${name?.first} ${name?.last}",
                    spaceHeight = R.dimen.distance_34x
                )

                // First name
                setupTitleItem(
                    textRes = R.string.title_first_name,
                    text = name?.first
                )

                // Last name
                setupTitleItem(textRes = R.string.title_last_name, text = name?.last)

                // Birthday
                setupTitleItem(
                    textRes = R.string.title_birthday,
                    text = viewUtil.convertStringDate(
                    formatDate = YEAR_DAY_MONTH_TIMEZONE,
                    desiredFormat = MONTH_DAY_YEAR,
                    inputDate = dob?.date
                    )
                )

                // Age
                setupTitleItem(
                    textRes = R.string.title_age,
                    text = viewUtil.calculateAge(
                    birthDate = dob?.date,
                    formatDate = YEAR_DAY_MONTH_TIMEZONE).toString()
                )

                // Last name
                setupTitleItem(textRes = R.string.title_gender, text = gender)

                // Email Address
                setupTitleItem(textRes = R.string.title_email, text = email)

                // Mobile Number
                setupTitleItem(textRes = R.string.title_mobile_number, text = cell)

                // Home Address
                setupTitleItem(textRes = R.string.title_address, text = location?.buildStreetAddress())
            }
        }
    }

    private fun Location.buildStreetAddress() = "${street?.number} ${street?.name}, $state $city, $country"

    private fun MutableList<Any>.setupTitleItem(
        text: String? = null,
        @StringRes textRes: Int? = null,
        @DimenRes spaceHeight: Int = R.dimen.distance_16x
    ) {
        add(element = SpaceItemViewDto(spaceHeight))
        add(element = TitleItemViewDto(title = TextLine(
            textRes = textRes,
            text = text)
        ))
    }
}