package com.module.data.repository

import com.module.data.db.MarsPhotosDao
import com.module.data.db.AppDatabase
import com.module.data.entities.MarsPhotoDataEntityMapper
import com.module.data.entities.MarsPhotoEntityDataMapper
import com.module.domain.entities.MarsPhotoSourcesEntity
import io.reactivex.Flowable

class MarsPhotoCacheImpl(private val database: AppDatabase,
                         private val entityToDataMapper: MarsPhotoEntityDataMapper,
                         private val dataToEntityMapper: MarsPhotoDataEntityMapper) : MarsPhotoDataStore {

    private val dao: MarsPhotosDao = database.getArticlesDao()

    override fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity> {
        return dao.getAllMarsPhotos().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun savePhotos(it: MarsPhotoSourcesEntity) {
        dao.clear()
        dao.saveAllMarsPhotos(it.photos.map { photos -> entityToDataMapper.mapPhotoToEntity(photos) })
    }

}