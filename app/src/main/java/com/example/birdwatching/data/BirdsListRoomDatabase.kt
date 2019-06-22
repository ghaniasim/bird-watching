package com.example.birdwatching

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.birdwatching.data.BirdsListDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [BirdsListItem::class], version = 1)
abstract class BirdsListRoomDatabase : RoomDatabase() {

    abstract fun birdsListDao(): BirdsListDao

    companion object {
        @Volatile
        private var INSTANCE: BirdsListRoomDatabase? = null

    fun getDatabase(
        context: Context,
        scope: CoroutineScope
    ): BirdsListRoomDatabase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BirdsListRoomDatabase::class.java,
                "word_database"
            )
                // Wipes and rebuilds instead of migrating if no Migration object.
                // Migration is not part of this codelab.
                .fallbackToDestructiveMigration()
                .addCallback(BirdListDatabaseCallback(scope))
                .build()
            INSTANCE = instance
            // return instance
            instance
        }

    }
    private class BirdListDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            INSTANCE?.let { database ->
                scope.launch {
                   // populateDatabase(database.wordDao())
                }
            }
        }
    }

}}


