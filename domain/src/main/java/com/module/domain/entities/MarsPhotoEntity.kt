package com.module.domain.entities

data class MarsPhotoEntity(
        var id: Int,
        var sol: Int,
        var url: String,
        var cameraId: Int,
        var cameraName: String,
        var cameraFullName: String
)