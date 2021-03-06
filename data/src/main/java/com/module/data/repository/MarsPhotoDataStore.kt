package com.module.data.repository

import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable


interface MarsPhotoDataStore{
    fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity>
}