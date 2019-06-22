package com.example.birdwatching

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface BirdsListDao {
    @Query("SELECT * from birds_list_table ORDER BY date DESC")
    fun getAllOrderByDateDesc(): LiveData<List<BirdsListItem>>

    @Query("SELECT * from birds_list_table ORDER BY date ASC")
    fun getAllOrderByDateAsc(): LiveData<List<BirdsListItem>>

    @Insert
    fun insert(item: BirdsListItem) : Long

    @Query("DELETE FROM birds_list_table WHERE id = :itemId")
    fun delete(itemId: Int)
}