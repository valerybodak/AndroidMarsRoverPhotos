package com.module.data.entities.response

import com.google.gson.annotations.SerializedName

data class MarsPhotoResponseData(
    @SerializedName("photos") var photos: List<MarsPhotoResponseItem> = emptyList()
)