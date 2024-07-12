package com.imrkjoseph.cybillteckexam.app.shared.dto

import com.imrkjoseph.cybillteckexam.app.component.TextLine

/**
 * Layout rendered in [com.imrkjoseph.cybillteckexam.R.layout.item_shared_title]
 * */
data class TitleItemViewDto(
    val title: TextLine = TextLine.EMPTY,
    val textSize: Float? = 20F
)