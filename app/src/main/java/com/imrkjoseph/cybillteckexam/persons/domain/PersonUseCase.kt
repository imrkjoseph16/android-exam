package com.imrkjoseph.cybillteckexam.persons.domain

import com.imrkjoseph.cybillteckexam.persons.data.repository.PersonRepository
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {

    suspend fun getPersonList(page: Int) = personRepository.getPersonList(page = page)
}