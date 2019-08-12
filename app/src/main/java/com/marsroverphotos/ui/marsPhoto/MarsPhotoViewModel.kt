package com.marsroverphotos.ui.marsPhoto

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.usecases.GetMarsPhotosUseCase
import com.marsroverphotos.common.BaseViewModel
import com.marsroverphotos.entities.Data
import com.marsroverphotos.entities.Error
import com.marsroverphotos.entities.Status

class MarsPhotoViewModel(private val getMarsPhotoUseCase: GetMarsPhotosUseCase) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mPhotos = MutableLiveData<Data<MarsPhotoSourcesEntity>>()

    fun fetchMarsPhotos(roverId: String) {
        val disposable = getMarsPhotoUseCase.getMarsPhotos(roverId)
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