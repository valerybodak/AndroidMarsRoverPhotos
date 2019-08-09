package com.module.domain.usecases

import com.module.domain.common.BaseFlowableUseCase
import com.module.domain.common.FlowableRxTransformer
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.repositories.MarsPhotoRepository
import io.reactivex.Flowable

//It will get you the only the latest by fetching it from remote
class GetRemoteMarsPhotosUseCase(private val transformer: FlowableRxTransformer<MarsPhotoSourcesEntity>,
                                 private val repositories: MarsPhotoRepository): BaseFlowableUseCase<MarsPhotoSourcesEntity>(transformer){

    companion object {
        private const val PARAM_ROVER_ID = "rover_id"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<MarsPhotoSourcesEntity> {
        return repositories.getMarsPhotos(data!!.get(PARAM_ROVER_ID).toString())
    }

    fun getMarsPhotos(roverId: String): Flowable<MarsPhotoSourcesEntity>{
        val data = HashMap<String, String>()
        data[PARAM_ROVER_ID] = roverId
        return single(data)
    }
}