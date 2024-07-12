package com.imrkjoseph.cybillteckexam.persons.data.client

import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApiClient {

    @GET("api/")
    suspend fun getPersonList(
        @Query("page")
        page: Int = 1,
        @Query("results")
        results: Int = 10
    ) : PersonListResponse
}