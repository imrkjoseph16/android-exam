package com.imrkjoseph.cybillteckexam.app.shared.local.domain

import com.imrkjoseph.cybillteckexam.app.shared.local.data.repository.LocalDatabaseRepository
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val localRepository: LocalDatabaseRepository
) {
    suspend fun getPersonList(page: Int) = if (page <= localRepository.getTotalCachedList()) localRepository.getPersonList(page) else PersonListResponse()

    suspend fun saveLocalPersonLists(response: PersonListResponse?) = localRepository.saveLocalPersonLists(response)

    suspend fun clearCachedData() = localRepository.clearCachedData()
}