package com.module.data.entities

import com.google.gson.annotations.SerializedName
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity

data class NewsSourcesData(
        @SerializedName("photos") var photos: List<MarsPhotoData> = emptyList()
)

class NewsDataEntityMapper constructor() {

    fun mapToEntity(data: NewsSourcesData?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
            photos = mapListArticlesToEntity(data?.photos)
    )

    fun mapToEntity(articles: List<MarsPhotoData>?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListArticlesToEntity(articles)
    )

    fun mapListArticlesToEntity(articles: List<MarsPhotoData>?)
            : List<MarsPhotoEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: MarsPhotoData): MarsPhotoEntity = MarsPhotoEntity(
            id = response.id,
            url = response.url
    )


}


class NewsEntityDataMapper constructor() {

    fun mapToEntity(data: MarsPhotoSourcesEntity?): NewsSourcesData? = NewsSourcesData(
        photos = mapListArticlesToEntity(data?.photos)
    )

    fun mapListArticlesToEntity(articles: List<MarsPhotoEntity>?)
            : List<MarsPhotoData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: MarsPhotoEntity): MarsPhotoData = MarsPhotoData(
            id = response.id,
            url = response.url
    )


}

