package com.module.domain.repositories

import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

interface MarsPhotoRepository {

    fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity>
    fun getLocalMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity>
    fun getRemoteMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity>

}