package com.example.birdwatching

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [BirdsListItem::class], version = 1)
abstract class BirdsListRoomDatabase : RoomDatabase() {
    abstract fun birdsListDao(): BirdsListDao
}
