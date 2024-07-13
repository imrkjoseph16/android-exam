package com.imrkjoseph.cybillteckexam.app.shared.local.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imrkjoseph.cybillteckexam.app.shared.local.data.dao.PersonListDetailsDao
import com.imrkjoseph.cybillteckexam.app.shared.local.data.model.LocalPersonLists
import com.imrkjoseph.cybillteckexam.app.util.Default.Companion.DB_PERSON_PATH
import com.imrkjoseph.cybillteckexam.app.util.LocalConverter

@Database(entities = [LocalPersonLists::class], version = 1)
@TypeConverters(LocalConverter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun personsDao() : PersonListDetailsDao

    companion object {
        @Volatile
        private var DB_INSTANCE: DatabaseService? = null

        fun getInstance(context: Context) = DB_INSTANCE ?: synchronized(this) {
            DB_INSTANCE ?: buildDatabase(context).also { DB_INSTANCE = it }
        }

        private fun buildDatabase(context: Context): DatabaseService {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    DB_PERSON_PATH
                ).allowMainThreadQueries().build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }
}