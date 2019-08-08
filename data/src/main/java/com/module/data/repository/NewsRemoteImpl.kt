package com.module.data.repository

import com.module.data.api.RemoteNasaApi
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.data.entities.NewsDataEntityMapper
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api:RemoteNasaApi): NewsDataStore {

    private val newsMapper =  NewsDataEntityMapper()

    override fun getNews(): Flowable<MarsPhotoSourcesEntity> {

        return api.getRoverPhotos().map { newsMapper.mapToEntity(it) }
    }

}