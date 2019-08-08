package com.module.data.repository

import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable


interface NewsDataStore{
    fun getNews(): Flowable<MarsPhotoSourcesEntity>
}