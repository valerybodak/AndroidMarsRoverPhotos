package com.module.data.repository

import com.module.data.db.ArticlesDao
import com.module.data.db.NewsDatabase
import com.module.data.entities.NewsDataEntityMapper
import com.module.data.entities.NewsEntityDataMapper
import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

class NewsCacheImpl(private val database: NewsDatabase,
                    private val entityToDataMapper: NewsEntityDataMapper,
                    private val dataToEntityMapper: NewsDataEntityMapper) : NewsDataStore {

    private val dao: ArticlesDao = database.getArticlesDao()

    override fun getNews(): Flowable<MarsPhotoSourcesEntity> {
        return dao.getAllArticles().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: MarsPhotoSourcesEntity) {
        dao.clear()
        dao.saveAllArticles(it.photos.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }

}