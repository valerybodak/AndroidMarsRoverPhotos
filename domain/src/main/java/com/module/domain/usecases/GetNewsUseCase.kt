package com.module.domain.usecases

import com.module.domain.common.BaseFlowableUseCase
import com.module.domain.common.FlowableRxTransformer
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.NewsRepository
import io.reactivex.Flowable

/**
 * It will first get photos from the local database and also update it with the latest
 * photos from remote
 */
class GetNewsUseCase(private val transformer: FlowableRxTransformer<MarsPhotoSourcesEntity>,
                     private val repositories: NewsRepository): BaseFlowableUseCase<MarsPhotoSourcesEntity>(transformer){

    override fun createFlowable(data: Map<String, Any>?): Flowable<MarsPhotoSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<MarsPhotoSourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }
}