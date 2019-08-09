package com.module.domain.usecases

import com.module.domain.common.BaseFlowableUseCase
import com.module.domain.common.FlowableRxTransformer
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.MarsPhotoRepository
import io.reactivex.Flowable

// It will just return the list of photos in local database
class GetLocalMarsPhotosUseCase(private val transformer: FlowableRxTransformer<MarsPhotoSourcesEntity>,
                                private val repositories: MarsPhotoRepository): BaseFlowableUseCase<MarsPhotoSourcesEntity>(transformer){

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<MarsPhotoSourcesEntity> {
        return repositories.getMarsPhotos()
    }

    fun getNews(): Flowable<MarsPhotoSourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }


}