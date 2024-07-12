package com.imrkjoseph.cybillteckexam.app.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imrkjoseph.cybillteckexam.app.shared.local.data.model.LocalData
import java.lang.reflect.Type

object LocalConverter {
    @TypeConverter
    fun fromData(value: String?): List<LocalData> {
        val listType: Type = object : TypeToken<List<LocalData?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromDataList(list: List<LocalData?>?): String = Gson().toJson(list)
}