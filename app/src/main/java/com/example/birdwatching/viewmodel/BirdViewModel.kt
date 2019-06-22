package com.example.birdwatching

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.birdwatching.data.BirdsListDao
import com.example.birdwatching.model.BirdsListItem
import javax.inject.Inject


class BirdViewModel (application: Application): ViewModel() {

    @Inject
    lateinit var birdDao : BirdsListDao

    val birdItems: LiveData<List<BirdsListItem>> = birdDao.getAllOrderByDateAsc()
}