package com.module.data.api

import com.module.data.entities.MarsPhotoSourcesData
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteNasaApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=fu6BdRZh8oyktYqAFwHXFUGVS2LgcpxQgkTMXtT3")
    fun getRoverPhotos(): Flowable<MarsPhotoSourcesData>

}