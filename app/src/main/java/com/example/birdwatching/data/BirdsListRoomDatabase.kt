package com.example.birdwatching.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.birdwatching.model.BirdsListItem

@Database(entities = [BirdsListItem::class], version = 1)
abstract class BirdsListRoomDatabase : RoomDatabase() {
    abstract fun birdsListDao(): BirdsListDao

    companion object {
        private var instance: BirdsListRoomDatabase? = null

        fun getInstance(context: Context): BirdsListRoomDatabase? {
            if (instance == null) {
                synchronized(BirdsListRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BirdsListRoomDatabase::class.java, "birds_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }

    }
}


