package com.module.domain.usecases

import com.module.domain.common.BaseFlowableUseCase
import com.module.domain.common.FlowableRxTransformer
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.MarsPhotoRepository
import io.reactivex.Flowable

/**
 * It will first get photos from the local database and also update it with the latest
 * photos from remote
 */
class GetMarsPhotosUseCase(private val transformer: FlowableRxTransformer<MarsPhotoSourcesEntity>,
                           private val repositories: MarsPhotoRepository): BaseFlowableUseCase<MarsPhotoSourcesEntity>(transformer){

    override fun createFlowable(data: Map<String, Any>?): Flowable<MarsPhotoSourcesEntity> {
        return repositories.getMarsPhotos()
    }

    fun getNews(): Flowable<MarsPhotoSourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }
}