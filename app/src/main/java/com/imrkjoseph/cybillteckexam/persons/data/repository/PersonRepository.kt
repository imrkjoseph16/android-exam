package com.imrkjoseph.cybillteckexam.persons.data.repository

import com.imrkjoseph.cybillteckexam.persons.data.client.PersonApiClient
import dagger.Lazy
import retrofit2.Retrofit
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val retrofit: Lazy<Retrofit>
) {

    private val apiClient: PersonApiClient by lazy { retrofit.get().create(PersonApiClient::class.java) }

    suspend fun getPersonList(page: Int) = apiClient.getPersonList(page = page)
}