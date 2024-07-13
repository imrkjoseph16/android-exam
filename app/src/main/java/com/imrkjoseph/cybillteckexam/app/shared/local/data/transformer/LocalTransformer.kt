package com.imrkjoseph.cybillteckexam.app.shared.local.data.transformer

import com.imrkjoseph.cybillteckexam.app.shared.local.data.model.LocalData
import com.imrkjoseph.cybillteckexam.app.shared.local.data.model.LocalPersonLists
import com.imrkjoseph.cybillteckexam.persons.data.dto.Dob
import com.imrkjoseph.cybillteckexam.persons.data.dto.Id
import com.imrkjoseph.cybillteckexam.persons.data.dto.Info
import com.imrkjoseph.cybillteckexam.persons.data.dto.Location
import com.imrkjoseph.cybillteckexam.persons.data.dto.Name
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import com.imrkjoseph.cybillteckexam.persons.data.dto.Picture
import com.imrkjoseph.cybillteckexam.persons.data.dto.Result
import com.imrkjoseph.cybillteckexam.persons.data.dto.Street
import javax.inject.Inject

class LocalTransformer @Inject constructor() {

    fun transformLocalToResponse(result: List<LocalPersonLists>?): PersonListResponse {
        val data: MutableList<Result> = mutableListOf()
        var personListResponse = PersonListResponse()
        result?.map { lists ->
            lists.data.map { localData ->
                data.add(element = Result(
                    id = Id(value = localData.id),
                    cell = localData.cell,
                    email = localData.email,
                    gender = localData.gender,
                    phone = localData.phone,
                    picture = Picture(large = localData.picture),
                    name = Name(
                        first = localData.first,
                        last = localData.last,
                        title = localData.title
                    ),
                    dob = Dob(
                        date = localData.birthDate,
                        age = localData.age
                    ),
                    location = Location(
                        city = localData.city,
                        country = localData.country,
                        state = localData.state,
                        street = Street(
                            name = localData.streetName,
                            number = localData.streetNumber
                        )
                    )
                ))
            }
            personListResponse = PersonListResponse().apply {
                info = Info(page = lists.page, results = lists.results)
                results = data
            }
        }
        return personListResponse
    }

    fun transformResponseToLocal(response: PersonListResponse?) = LocalPersonLists(
        data = response?.results?.map { details ->
            LocalData(
                id = details.id.value,
                cell = details.cell,
                age = details.dob?.age,
                birthDate = details.dob?.date,
                email = details.email,
                gender = details.gender,
                city = details.location?.city,
                country = details.location?.country,
                state = details.location?.state,
                streetName = details.location?.street?.name,
                streetNumber = details.location?.street?.number,
                first = details.name?.first,
                last = details.name?.last,
                title = details.name?.title,
                phone = details.phone,
                picture = details.picture?.large,
            )
        } ?: emptyList(),
        page = response?.info?.page,
        results = response?.info?.results
    )
}