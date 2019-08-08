package com.module.domain.repositories

import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<MarsPhotoSourcesEntity>
    fun getLocalNews(): Flowable<MarsPhotoSourcesEntity>
    fun getRemoteNews(): Flowable<MarsPhotoSourcesEntity>

}