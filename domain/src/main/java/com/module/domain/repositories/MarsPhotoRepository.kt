package com.module.domain.repositories

import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

interface MarsPhotoRepository {

    fun getMarsPhotos(): Flowable<MarsPhotoSourcesEntity>
    fun getLocalMarsPhotos(): Flowable<MarsPhotoSourcesEntity>
    fun getRemoteMarsPhotos(): Flowable<MarsPhotoSourcesEntity>

}