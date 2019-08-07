package com.marsroverphotos.entities

data class NewsSources(
        var status: String? = null,
        var articles: List<NewsPublisher> = emptyList()
)