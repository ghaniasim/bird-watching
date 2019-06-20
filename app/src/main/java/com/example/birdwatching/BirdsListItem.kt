package com.example.birdwatching

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "birds_list_table")
data class BirdsListItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String?,
    var date: String
)
