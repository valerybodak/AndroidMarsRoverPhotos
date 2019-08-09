package com.marsroverphotos.marsPhoto

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.module.domain.common.Mapper
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.usecases.GetMarsPhotosUseCase
import com.marsroverphotos.common.BaseViewModel
import com.marsroverphotos.entities.Data
import com.marsroverphotos.entities.Error
import com.marsroverphotos.entities.MarsPhotoSources
import com.marsroverphotos.entities.Status

class MarsPhotoViewModel(private val getNewsUseCase: GetMarsPhotosUseCase,
                         private val mapper: Mapper<MarsPhotoSourcesEntity, MarsPhotoSources>) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mPhotos = MutableLiveData<Data<MarsPhotoSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
                .flatMap { mapper.Flowable(it) }
                .subscribe({ response ->
                    Log.d(TAG, "On Next Called")
                    mPhotos.value = Data(responseType = Status.SUCCESSFUL, data = response)
                }, { error ->
                    Log.d(TAG, "On Error Called")
                    mPhotos.value = Data(responseType = Status.ERROR, error = Error(error.message))
                }, {
                    Log.d(TAG, "On Complete Called")
                })

        addDisposable(disposable)
    }

    fun getMarsPhotoLiveData() = mPhotos
}