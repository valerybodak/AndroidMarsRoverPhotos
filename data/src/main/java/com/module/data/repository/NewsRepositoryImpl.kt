package com.module.data.repository

import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.NewsRepository
import io.reactivex.Flowable

class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override fun getLocalNews(): Flowable<MarsPhotoSourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteNews(): Flowable<MarsPhotoSourcesEntity> {
        return remote.getNews()
    }

    override fun getNews(): Flowable<MarsPhotoSourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
                .mergeWith(updateNewsFlowable.doOnNext{
                    remoteNews -> cache.saveArticles(remoteNews)
                })
    }
}