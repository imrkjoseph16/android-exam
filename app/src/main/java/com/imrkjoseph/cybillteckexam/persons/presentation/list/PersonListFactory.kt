package com.imrkjoseph.cybillteckexam.persons.presentation.list

import androidx.annotation.StringRes
import com.imrkjoseph.cybillteckexam.R
import com.imrkjoseph.cybillteckexam.app.component.TextLine
import com.imrkjoseph.cybillteckexam.app.shared.binder.PersonListItem
import com.imrkjoseph.cybillteckexam.app.shared.dto.ListItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.cybillteckexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import com.imrkjoseph.cybillteckexam.persons.data.dto.Result
import javax.inject.Inject

class PersonListFactory @Inject constructor() {

    fun createOverview(data: PersonListResponse) = data.prepareList()

    private fun PersonListResponse.prepareList() = buildList {
        // Title
        add(element = SpaceItemViewDto(R.dimen.distance_20x))
        add(element = setupSectionTitle(textRes = R.string.title_persons_list, textSize = 32F))

        // Person Lists
        add(element = SpaceItemViewDto(R.dimen.distance_16x))
        setupPersonList(data = results)
    }

    private fun MutableList<Any>.setupPersonList(data: List<Result>?) {
        data?.sortedBy { it.name?.first }?.map { details ->
            details.id.value?.let { id ->
                // Alphabetical Letter
                setupAlphabeticalLetters(details.name?.first?.first().toString())

                // Person Lists
                add(element = PersonListItem(
                        id = id,
                        dto = ListItemViewDto(
                            itemId = id,
                            firstLine = TextLine(
                                text = "${details.name?.first} ${details.name?.last}",
                                textRes = R.string.title_full_name
                            ),
                            secondLine = TextLine(
                                text = details.email,
                                textRes = R.string.title_email
                            ),
                            thirdLine = TextLine(
                                text = details.gender,
                                textRes = R.string.title_gender
                            ),
                            avatarUrl = details.picture?.large
                        )
                    )
                )

                add(element = SpaceItemViewDto(R.dimen.distance_12x))
            }
        }
    }

    private fun MutableList<Any>.setupAlphabeticalLetters(letter: String) {
        val alphabeticalOrderItem = setupSectionTitle(text = letter, textSize = 18F)
        if (this.contains(alphabeticalOrderItem).not()) add(element = alphabeticalOrderItem)
    }

    private fun setupSectionTitle(
        @StringRes textRes: Int? = null,
        text: String? = null,
        textSize: Float? = null
    ) = TitleItemViewDto(
        title = TextLine(textRes = textRes, text = text),
        textSize = textSize
    )
}