package com.module.data.entities

import com.google.gson.annotations.SerializedName
import com.module.data.entities.response.RoverCameraResponse
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity

data class MarsPhotoResponseData(
    @SerializedName("photos") var photos: List<MarsPhotoResponseItem> = emptyList()
)

data class MarsPhotoResponseItem(
    @SerializedName("id") var id: Int,
    @SerializedName("sol") var sol: Int,
    @SerializedName("img_src") var url: String,
    @SerializedName("camera") var camera: RoverCameraResponse
)

class MarsPhotoDataMapper {

    fun mapToEntity(data: MarsPhotoResponseData?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListResponseItems(data?.photos)
    )

    fun mapToEntity(photos: List<MarsPhotoDbItem>?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListDbItems(photos)
    )
    private fun mapListResponseItems(photos: List<MarsPhotoResponseItem>?)
            : List<MarsPhotoEntity> = photos?.map { mapPhotoResponseItemToEntity(it) } ?: emptyList()

    private fun mapListDbItems(photos: List<MarsPhotoDbItem>?)
            : List<MarsPhotoEntity> = photos?.map { mapPhotoDbItemToEntity(it) } ?: emptyList()

    private fun mapPhotoResponseItemToEntity(item: MarsPhotoResponseItem): MarsPhotoEntity = MarsPhotoEntity(
        id = item.id,
        url = item.url,
        sol = item.sol,
        cameraId = item.camera.id,
        cameraName = item.camera.name,
        cameraFullName = item.camera.fullName
    )

    private fun mapPhotoDbItemToEntity(item: MarsPhotoDbItem): MarsPhotoEntity = MarsPhotoEntity(
        id = item.id,
        url = item.url,
        sol = item.sol,
        cameraId = item.cameraId,
        cameraName = item.cameraName,
        cameraFullName = item.cameraFullName
    )

    fun mapEntityToDbEntity(entity: MarsPhotoEntity): MarsPhotoDbItem = MarsPhotoDbItem(
        id = entity.id,
        sol = entity.sol,
        url = entity.url,
        cameraId = entity.cameraId,
        cameraName = entity.cameraName,
        cameraFullName = entity.cameraFullName
    )
}