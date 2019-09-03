package com.marsroverphotos.ui.marsPhoto

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.module.domain.entities.MarsPhotoSourcesEntity
import com.module.domain.usecases.GetMarsPhotosUseCase
import com.marsroverphotos.common.BaseViewModel
import com.marsroverphotos.entities.Data
import com.module.domain.entities.ErrorEntity
import com.marsroverphotos.entities.Status
import com.module.data.entities.response.ErrorResponse
import retrofit2.HttpException

class MarsPhotoViewModel(private val getMarsPhotoUseCase: GetMarsPhotosUseCase) : BaseViewModel() {

    companion object {
        private val TAG = "MarsPhotoViewModel"
    }

    var mPhotos = MutableLiveData<Data<MarsPhotoSourcesEntity>>()

    fun fetchMarsPhotos(roverId: String) {
        val disposable = getMarsPhotoUseCase.getMarsPhotos(roverId)
                .subscribe({ response ->
                    Log.d(TAG, "On Next Called")
                    mPhotos.value = Data(responseType = Status.SUCCESSFUL, data = response)
                }, { error ->
                    if(error is HttpException){
                        val responseBody = error.response().errorBody()?.string()

                        val gson = Gson()
                        val errorResponse = gson.fromJson<ErrorResponse>(
                            responseBody,
                            ErrorResponse::class.java)

                        mPhotos.value = Data(responseType = Status.ERROR, error = errorResponse.error)

                    }
                }, {
                    Log.d(TAG, "On Complete Called")
                })

        addDisposable(disposable)
    }

    fun getMarsPhotoLiveData() = mPhotos
}