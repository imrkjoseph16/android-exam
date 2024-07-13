package com.imrkjoseph.cybillteckexam.app.shared.local.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.imrkjoseph.cybillteckexam.app.util.Default.Companion.DB_PERSON_LIST
import com.imrkjoseph.cybillteckexam.app.util.LocalConverter

@Entity(tableName = DB_PERSON_LIST)
data class LocalPersonLists(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("data")
    @TypeConverters(LocalConverter::class)
    val data: List<LocalData> = emptyList(),

    @ColumnInfo("page")
    val page: Int? = null,

    @ColumnInfo("results")
    val results: Int? = null
)

data class LocalData(
    @ColumnInfo("id")
    val id: String? = null,

    @ColumnInfo("cell")
    val cell: String? = null,

    @ColumnInfo("age")
    val age: Int? = null,

    @ColumnInfo("birthDate")
    val birthDate: String? = null,

    @ColumnInfo("email")
    val email: String? = null,

    @ColumnInfo("gender")
    val gender: String? = null,

    @ColumnInfo("city")
    val city: String? = null,

    @ColumnInfo("country")
    val country: String? = null,

    @ColumnInfo("state")
    val state: String? = null,

    @ColumnInfo("street_name")
    val streetName: String? = null,

    @ColumnInfo("street_number")
    val streetNumber: Long? = null,

    @ColumnInfo("first")
    val first: String? = null,

    @ColumnInfo("last")
    val last: String? = null,

    @ColumnInfo("title")
    val title: String? = null,

    @ColumnInfo("phone")
    val phone: String? = null,

    @ColumnInfo("picture")
    val picture: String? = null
)
