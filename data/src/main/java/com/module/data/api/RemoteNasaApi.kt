package com.module.data.api

import com.module.data.BuildConfig
import com.module.data.entities.NewsSourcesData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteNasaApi {

    @GET("top-headlines?country=us")
    fun getRoverPhotos(): Flowable<NewsSourcesData>

}