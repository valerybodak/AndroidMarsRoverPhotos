package com.marsroverphotos.mappers

import com.module.domain.common.Mapper
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.marsroverphotos.entities.MarsPhoto
import com.marsroverphotos.entities.MarsPhotoSources

class MarsPhotoEntityMapper : Mapper<MarsPhotoSourcesEntity, MarsPhotoSources>() {
    override fun mapFrom(data: MarsPhotoSourcesEntity): MarsPhotoSources = MarsPhotoSources(
        photos = mapListArticlesToPresetation(data?.photos)
    )

    private fun mapListArticlesToPresetation(articles: List<MarsPhotoEntity>?)
            : List<MarsPhoto> = articles?.map { mapArticleToPresentation(it) }
            ?: emptyList()

    private fun mapArticleToPresentation(response: MarsPhotoEntity): MarsPhoto = MarsPhoto(
            id = response.id,
            url = response.url
    )

}