package com.module.data.entities.response

import com.google.gson.annotations.SerializedName

data class RoverCameraResponse(

    @SerializedName("camera") var id: Int,
    @SerializedName("camera") var name: String,
    @SerializedName("camera") var rover_id: Int,
    @SerializedName("full_name") var fullName: String
)