package com.imrkjoseph.cybillteckexam.app.shared.dto

import com.imrkjoseph.cybillteckexam.app.component.TextLine

/**
 * Reusable component
 *
 * Describes data rendered in [com.imrkjoseph.cybillteckexam.R.layout.item_shared_list]
 * */
data class ListItemViewDto(
    val itemId: String,
    val firstLine: TextLine? = TextLine.EMPTY,
    val secondLine: TextLine? = TextLine.EMPTY,
    val thirdLine: TextLine? = TextLine.EMPTY,
    val avatarUrl: String? = null
)