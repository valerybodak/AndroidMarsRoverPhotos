package com.module.data.repository

import com.module.data.api.RemoteNasaApi
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.data.entities.MarsPhotoDataEntityMapper
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api:RemoteNasaApi): NewsDataStore {

    private val newsMapper =  MarsPhotoDataEntityMapper()

    override fun getNews(): Flowable<MarsPhotoSourcesEntity> {

        return api.getRoverPhotos().map { newsMapper.mapToEntity(it) }
    }

}