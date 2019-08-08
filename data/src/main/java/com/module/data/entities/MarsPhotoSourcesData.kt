package com.module.data.entities

import com.google.gson.annotations.SerializedName
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity

data class MarsPhotoSourcesData(
        @SerializedName("photos") var photos: List<MarsPhotoData> = emptyList()
)

class MarsPhotoDataEntityMapper constructor() {

    fun mapToEntity(data: MarsPhotoSourcesData?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
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


class NewsEntityDataMapper constructor() {

    fun mapToEntity(data: MarsPhotoSourcesEntity?): MarsPhotoSourcesData? = MarsPhotoSourcesData(
        photos = mapListArticlesToEntity(data?.photos)
    )

    fun mapListArticlesToEntity(articles: List<MarsPhotoEntity>?)
            : List<MarsPhotoData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: MarsPhotoEntity): MarsPhotoData = MarsPhotoData(
            id = response.id,
            url = response.url
    )


}

