package com.module.data.api

import com.module.data.entities.MarsPhotoSourcesData
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteNasaApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz")
    fun getMarsRoverPhotos(): Flowable<MarsPhotoSourcesData>

}