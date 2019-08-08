package com.module.data.repository

import com.module.data.db.MarsPhotosDao
import com.module.data.db.AppDatabase
import com.module.data.entities.MarsPhotoDataEntityMapper
import com.module.data.entities.NewsEntityDataMapper
import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

class NewsCacheImpl(private val database: AppDatabase,
                    private val entityToDataMapper: NewsEntityDataMapper,
                    private val dataToEntityMapper: MarsPhotoDataEntityMapper) : NewsDataStore {

    private val dao: MarsPhotosDao = database.getArticlesDao()

    override fun getNews(): Flowable<MarsPhotoSourcesEntity> {
        return dao.getAllMarsPhotos().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: MarsPhotoSourcesEntity) {
        dao.clear()
        dao.saveAllMarsPhotos(it.photos.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }

}