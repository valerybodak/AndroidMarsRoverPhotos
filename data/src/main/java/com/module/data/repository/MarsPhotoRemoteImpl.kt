package com.module.data.repository

import com.module.data.api.RemoteNasaApi
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.data.entities.MarsPhotoResponseDataMapper
import io.reactivex.Flowable

class MarsPhotoRemoteImpl constructor(private val api: RemoteNasaApi) : MarsPhotoDataStore {

    private val mapper = MarsPhotoResponseDataMapper()

    override fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity> {

        return api.getMarsRoverPhotos(roverId).map { mapper.mapToEntity(it) }
    }

}