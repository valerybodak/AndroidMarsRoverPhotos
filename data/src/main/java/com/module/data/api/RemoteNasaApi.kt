package com.module.data.api

import com.module.data.BuildConfig
import com.module.data.entities.NewsSourcesData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteNasaApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=fu6BdRZh8oyktYqAFwHXFUGVS2LgcpxQgkTMXtT3")
    fun getRoverPhotos(): Flowable<NewsSourcesData>

}