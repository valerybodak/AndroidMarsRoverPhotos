package com.module.data.repository

import com.module.data.api.RemoteNewsApi
import com.module.domain.entities.NewsSourcesEntity
import com.module.data.entities.NewsDataEntityMapper
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api:RemoteNewsApi): NewsDataStore {

    private val newsMapper =  NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {

        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}