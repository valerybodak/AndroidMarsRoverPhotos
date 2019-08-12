package com.module.data.entities

import com.google.gson.annotations.SerializedName
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity

data class MarsPhotoResponseData(
        @SerializedName("photos") var photos: List<MarsPhotoData> = emptyList()
)

class MarsPhotoDataEntityMapper {

    fun mapToEntity(data: MarsPhotoResponseData?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
            photos = mapListPhotosToEntity(data?.photos)
    )

    fun mapToEntity(photos: List<MarsPhotoData>?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListPhotosToEntity(photos)
    )

    fun mapListPhotosToEntity(photos: List<MarsPhotoData>?)
            : List<MarsPhotoEntity> = photos?.map { mapPhotoToEntity(it) } ?: emptyList()

    fun mapPhotoToEntity(response: MarsPhotoData): MarsPhotoEntity = MarsPhotoEntity(
            id = response.id,
            url = response.url

    )
}

class MarsPhotoEntityDataMapper {

    fun mapToEntity(data: MarsPhotoSourcesEntity?): MarsPhotoResponseData? = MarsPhotoResponseData(
        photos = mapListPhotosToEntity(data?.photos)
    )

    fun mapListPhotosToEntity(photos: List<MarsPhotoEntity>?)
            : List<MarsPhotoData> = photos?.map { mapPhotoToEntity(it) } ?: emptyList()

    fun mapPhotoToEntity(response: MarsPhotoEntity): MarsPhotoData = MarsPhotoData(
            id = response.id,
            url = response.url
    )


}

