package com.imrkjoseph.cybillteckexam.persons.presentation.list

import com.imrkjoseph.cybillteckexam.persons.data.dto.Result

data class PersonState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val uiItems: List<Any> = emptyList(),
    var cachedPersonList: MutableList<Result> = mutableListOf(),
    var currentPage: Int = 1,
)