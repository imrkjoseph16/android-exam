package com.imrkjoseph.cybillteckexam.persons.data.dto

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

data class PersonListResponse(
    @JsonProperty("info")
    val info: Info? = null,

    @JsonProperty("results")
    val results: List<Result>
)

data class Info(
    @JsonProperty("page")
    val page: Int,

    @JsonProperty("results")
    val results: Int,

    @JsonProperty("seed")
    val seed: String,

    @JsonProperty("version")
    val version: String
)

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Result(
    @JsonProperty("cell")
    val cell: String,

    @JsonProperty("dob")
    val dob: Dob,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("gender")
    val gender: String,

    @JsonProperty("id")
    val id: Id,

    @JsonProperty("location")
    val location: Location,

    @JsonProperty("name")
    val name: Name,

    @JsonProperty("nat")
    val nat: String,

    @JsonProperty("phone")
    val phone: String,

    @JsonProperty("picture")
    val picture: Picture
)  : Parcelable

@Parcelize
data class Dob(
    @JsonProperty("age")
    val age: Int,

    @JsonProperty("date")
    val date: String
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
    val city: String,

    @JsonProperty("country")
    val country: String,

    @JsonProperty("state")
    val state: String,

    @JsonProperty("street")
    val street: Street
) : Parcelable

@Parcelize
data class Name(
    @JsonProperty("first")
    val first: String,

    @JsonProperty("last")
    val last: String,

    @JsonProperty("title")
    val title: String
) : Parcelable

@Parcelize
data class Picture(
    @JsonProperty("large")
    val large: String,

    @JsonProperty("medium")
    val medium: String,

    @JsonProperty("thumbnail")
    val thumbnail: String
) : Parcelable

@Parcelize
data class Street(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("number")
    val number: Long
) : Parcelable