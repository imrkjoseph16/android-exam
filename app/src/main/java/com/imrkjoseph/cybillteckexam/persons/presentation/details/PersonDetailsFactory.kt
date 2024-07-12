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
                add(element = SpaceItemViewDto(R.dimen.distance_24x))
                add(element = AvatarItemViewDto(avatarUrl = picture?.large))

                // First name
                setupTitleItem(
                    textRes = R.string.title_first_name,
                    text = "${name?.title}. ${name?.first}",
                    spaceHeight = R.dimen.distance_30x
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

                // Email Address
                setupTitleItem(textRes = R.string.title_email, text = email)

                // Mobile Number
                setupTitleItem(textRes = R.string.title_mobile_number, text = cell)

                // Home Address
                setupTitleItem( textRes = R.string.title_address, text = location?.buildStreetAddress())
            }
        }
    }

    private fun Location.buildStreetAddress() = "${street?.number} ${street?.name}, $state $city, $country"

    private fun MutableList<Any>.setupTitleItem(
        text: String? = null,
        @StringRes textRes: Int? = null,
        @DimenRes spaceHeight: Int = R.dimen.distance_8x
    ) {
        add(element = SpaceItemViewDto(spaceHeight))
        add(element = TitleItemViewDto(title = TextLine(
            textRes = textRes,
            text = text)
        ))
    }
}