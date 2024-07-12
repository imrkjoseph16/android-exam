package com.imrkjoseph.cybillteckexam.persons.presentation.list

import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
data class PersonState(
    val loading: Boolean = true,
    val error: Throwable? = null,
    val response: PersonListResponse? = null,
    val uiItems: List<Any> = emptyList()
)