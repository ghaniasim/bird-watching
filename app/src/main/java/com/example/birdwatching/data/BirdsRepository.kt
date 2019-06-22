package com.example.birdwatching.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.birdwatching.model.BirdsListItem

class BirdsRepository(application: Application) {
    private var birdsDao: BirdsListDao
    private var allBirds: LiveData<List<BirdsListItem>>

    init {
        val database: BirdsListRoomDatabase = BirdsListRoomDatabase.getInstance(
            application.applicationContext
        )!!
        birdsDao = database.birdsListDao()
        allBirds = birdsDao.getAllOrderByDateAsc()
    }

    fun getAllBirdsAsc(): LiveData<List<BirdsListItem>> {
        allBirds = birdsDao.getAllOrderByDateAsc()
        return allBirds
    }

    fun getAllBirdsDesc(): LiveData<List<BirdsListItem>> {
        allBirds = birdsDao.getAllOrderByDateDesc()
        return allBirds
    }

    fun insert(bird: BirdsListItem) {
        val insertBirdAsyncTask = InsertBirdAsyncTask(birdsDao).execute(bird)
    }

    fun delete(id: Int) {
        val deleteBirdAsyncTask = DeleteBirdAsyncTask(birdsDao).execute(id)
    }

    private class InsertBirdAsyncTask(birdDao: BirdsListDao) : AsyncTask<BirdsListItem, Unit, Unit>() {
        val birdDao = birdDao
        override fun doInBackground(vararg p0: BirdsListItem?) {
            birdDao.insert(p0[0]!!)
        }
    }

    private class DeleteBirdAsyncTask(birdDao: BirdsListDao) : AsyncTask<Int, Unit, Unit>() {
        val birdDao = birdDao
        override fun doInBackground(vararg p0: Int?) {
            birdDao.delete(p0[0]!!)
        }
    }
}

