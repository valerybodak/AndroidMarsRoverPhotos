package com.marsroverphotos.mappers

import com.module.domain.common.Mapper
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.marsroverphotos.entities.NewsPublisher
import com.marsroverphotos.entities.NewsSources

class NewsEntityMapper : Mapper<MarsPhotoSourcesEntity, NewsSources>() {
    override fun mapFrom(data: MarsPhotoSourcesEntity): NewsSources = NewsSources(
        articles = mapListArticlesToPresetation(data?.photos)
    )

    private fun mapListArticlesToPresetation(articles: List<MarsPhotoEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
            ?: emptyList()

    private fun mapArticleToPresentation(response: MarsPhotoEntity): NewsPublisher = NewsPublisher(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )

}