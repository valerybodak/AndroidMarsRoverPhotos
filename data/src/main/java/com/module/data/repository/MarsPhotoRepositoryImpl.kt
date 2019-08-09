package com.module.data.repository

import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.MarsPhotoRepository
import io.reactivex.Flowable

class MarsPhotoRepositoryImpl(private val remote: MarsPhotoRemoteImpl,
                              private val cache: MarsPhotoCacheImpl) : MarsPhotoRepository {

    override fun getLocalMarsPhotos(): Flowable<MarsPhotoSourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteMarsPhotos(): Flowable<MarsPhotoSourcesEntity> {
        return remote.getNews()
    }

    override fun getMarsPhotos(): Flowable<MarsPhotoSourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
                .mergeWith(updateNewsFlowable.doOnNext{
                    remoteNews -> cache.saveArticles(remoteNews)
                })
    }
}