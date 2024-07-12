package com.imrkjoseph.cybillteckexam.persons.data.dto

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

data class PersonListResponse(
    @JsonProperty("info")
    var info: Info? = null,

    @JsonProperty("results")
    var results: List<Result> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Info(
    @JsonProperty("page")
    val page: Int? = null,

    @JsonProperty("results")
    val results: Int? = null
)

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Result(
    @JsonProperty("id")
    val id: Id,

    @JsonProperty("cell")
    val cell: String? = null,

    @JsonProperty("dob")
    val dob: Dob? = null,

    @JsonProperty("email")
    val email: String? = null,

    @JsonProperty("gender")
    val gender: String? = null,

    @JsonProperty("location")
    val location: Location? = null,

    @JsonProperty("name")
    val name: Name? = null,

    @JsonProperty("nat")
    val nat: String? = null,

    @JsonProperty("phone")
    val phone: String? = null,

    @JsonProperty("picture")
    val picture: Picture? = null
)  : Parcelable

@Parcelize
data class Dob(
    @JsonProperty("age")
    val age: Int? = null,

    @JsonProperty("date")
    val date: String? = null
) : Parcelable

@Parcelize
data class Id(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("value")
    val value: String? = null
) : Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Location(
    @JsonProperty("city")
    val city: String? = null,

    @JsonProperty("country")
    val country: String? = null,

    @JsonProperty("state")
    val state: String? = null,

    @JsonProperty("street")
    val street: Street? = null
) : Parcelable

@Parcelize
data class Name(
    @JsonProperty("first")
    val first: String? = null,

    @JsonProperty("last")
    val last: String? = null,

    @JsonProperty("title")
    val title: String? = null
) : Parcelable

@Parcelize
data class Picture(
    @JsonProperty("large")
    val large: String? = null,

    @JsonProperty("medium")
    val medium: String? = null,

    @JsonProperty("thumbnail")
    val thumbnail: String? = null
) : Parcelable

@Parcelize
data class Street(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("number")
    val number: Long? = null
) : Parcelable