package com.imrkjoseph.cybillteckexam.app.shared.local.domain

import com.imrkjoseph.cybillteckexam.app.shared.local.data.repository.LocalDatabaseRepository
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val localRepository: LocalDatabaseRepository
) {
    suspend fun getPersonList(page: Int) = localRepository.getPersonList(page)

    suspend fun saveLocalPersonLists(response: PersonListResponse?) = localRepository.saveLocalPersonLists(response)
}