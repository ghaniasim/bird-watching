package com.example.birdwatching

import com.example.birdwatching.data.BirdsListDao
import javax.inject.Inject

class BirdRepository  @Inject constructor(private val birdDao: BirdsListDao) : BaseRepository<BirdsListItem>(){
    override fun insert(t: BirdsListItem): Single<Long> = Single.fromCallable {
        birdDao.insert(t)
    }

    override fun delete(t: BirdsListItem): Completable = Completable.fromCallable {
        birdDao.delete(t)
    }

    fun getAllBirds(): Single<List<BirdsListItem>> = Single.create<List<BookItem>> {
        it.onSuccess(birdDao.getAllBooks())
    }


}
