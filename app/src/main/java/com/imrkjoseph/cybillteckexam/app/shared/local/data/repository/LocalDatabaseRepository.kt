package com.imrkjoseph.cybillteckexam.app.shared.local.data.repository

import com.imrkjoseph.cybillteckexam.app.shared.local.data.transformer.LocalTransformer
import com.imrkjoseph.cybillteckexam.app.shared.local.domain.DatabaseService
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import javax.inject.Inject

class LocalDatabaseRepository @Inject constructor(
    databaseService: DatabaseService,
    private val localTransformer: LocalTransformer
) {

    private val localClient = databaseService.personsDao()

    suspend fun getPersonList(page: Int) =  localTransformer.transformLocalToResponse(result = localClient.getPersonList(page))

    suspend fun saveLocalPersonLists(response: PersonListResponse?) = localClient.saveLocalPersonLists(
        personLists = localTransformer.transformResponseToLocal(response = response)
    )
}