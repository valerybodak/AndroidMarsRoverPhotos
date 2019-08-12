package com.module.data.api

import com.module.data.entities.MarsPhotoResponseData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteNasaApi {

    @GET("mars-photos/api/v1/rovers/{rover_id}/photos?sol=998&camera=fhaz")
    fun getMarsRoverPhotos(@Path("rover_id") roverId: String): Flowable<MarsPhotoResponseData>

}