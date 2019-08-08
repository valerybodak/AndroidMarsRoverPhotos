package com.module.domain.usecases

import com.module.domain.common.BaseFlowableUseCase
import com.module.domain.common.FlowableRxTransformer
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.NewsRepository
import io.reactivex.Flowable

//It will get you the only the latest by fetching it from remote
class GetRemoteNewsUseCase(private val transformer: FlowableRxTransformer<MarsPhotoSourcesEntity>,
                           private val repositories: NewsRepository): BaseFlowableUseCase<MarsPhotoSourcesEntity>(transformer){

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<MarsPhotoSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<MarsPhotoSourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }


}