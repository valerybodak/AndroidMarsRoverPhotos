package com.module.data.repository

import com.module.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.Single


interface NewsDataStore{
    fun getNews(): Flowable<NewsSourcesEntity>
}