package com.example.birdwatching.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.birdwatching.model.BirdsListItem
import com.example.birdwatching.data.BirdsRepository

class BirdViewModel (application: Application): AndroidViewModel(application) {
    private var repository: BirdsRepository = BirdsRepository(application)
    private var allBirds: LiveData<List<BirdsListItem>> = repository.getAllBirdsDesc()

    fun insert(bird: BirdsListItem) {
        repository.insert(bird)
    }

    fun getAllBirds(): LiveData<List<BirdsListItem>> {
        return allBirds
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}
