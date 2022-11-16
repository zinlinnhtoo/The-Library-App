package com.example.thelibraryapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.persistance.daos.BookDao
import com.example.thelibraryapp.persistance.daos.OverviewDao

@Database(
    entities = [
        OverviewListVO::class,
        BookVO::class
    ], version = 1, exportSchema = false
)
abstract class TheLibraryDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "THE_LIBRARY_DB"

        var dbInstance: TheLibraryDatabase? = null

        fun getDBInstance(context: Context): TheLibraryDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(
                        context,
                        TheLibraryDatabase::class.java,
                        DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun overviewDao(): OverviewDao
    abstract fun bookDao(): BookDao
}