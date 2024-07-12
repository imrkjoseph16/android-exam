package com.imrkjoseph.cybillteckexam.app.shared.local.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imrkjoseph.cybillteckexam.app.shared.local.data.model.LocalPersonLists
import com.imrkjoseph.cybillteckexam.app.util.Default.Companion.DB_PERSON_LIST

@Dao
interface PersonListDetailsDao {

    @Query("SELECT * FROM $DB_PERSON_LIST WHERE page = :page")
    suspend fun getPersonList(page: Int) : List<LocalPersonLists>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveLocalPersonLists(personLists: LocalPersonLists) : Long

    @Query("DELETE FROM $DB_PERSON_LIST")
    suspend fun clearCachedData()

    @Query("SELECT COUNT(id) FROM $DB_PERSON_LIST")
    suspend fun getTotalCachedList(): Int
}