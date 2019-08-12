package com.module.data.entities.response

import com.google.gson.annotations.SerializedName

data class MarsPhotoResponseItem(

    @SerializedName("id") var id: Int,
    @SerializedName("sol") var sol: Int,
    @SerializedName("img_src") var url: String,
    @SerializedName("camera") var camera: RoverCameraResponse
)