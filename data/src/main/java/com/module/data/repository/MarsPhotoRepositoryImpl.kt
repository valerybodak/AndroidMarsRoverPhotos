package com.module.data.repository

import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.MarsPhotoRepository
import io.reactivex.Flowable

class MarsPhotoRepositoryImpl(private val remote: MarsPhotoRemoteImpl,
                              private val cache: MarsPhotoCacheImpl) : MarsPhotoRepository {

    override fun getLocalMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity> {
        return cache.getMarsPhotos(roverId)
    }

    override fun getRemoteMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity> {
        return remote.getMarsPhotos(roverId)
    }

    override fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity> {
        val updatePhotosFlowable = remote.getMarsPhotos(roverId)
        return cache.getMarsPhotos(roverId)
                .mergeWith(updatePhotosFlowable.doOnNext{
                    remoteNews -> cache.savePhotos(remoteNews)
                })
    }
}