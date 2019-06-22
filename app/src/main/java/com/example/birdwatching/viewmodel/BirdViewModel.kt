package com.example.birdwatching.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.birdwatching.model.BirdsListItem
import com.example.birdwatching.data.BirdsRepository

class BirdViewModel (application: Application): AndroidViewModel(application) {
    private var repository: BirdsRepository = BirdsRepository(application)
    private var allBirdsDesc: LiveData<List<BirdsListItem>> = repository.getAllBirdsDesc()
    private var allBirdsAsc: LiveData<List<BirdsListItem>> = repository.getAllBirdsAsc()

    fun insert(bird: BirdsListItem) {
        repository.insert(bird)
    }

    fun getAllBirdsDesc(): LiveData<List<BirdsListItem>> {
        return allBirdsDesc
    }

    fun getAllBirdsAsc(): LiveData<List<BirdsListItem>> {
        return allBirdsAsc
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}
