package com.marsroverphotos.news

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

class NewsViewModel(private val getNewsUseCase: GetMarsPhotosUseCase,
                    private val mapper: Mapper<MarsPhotoSourcesEntity, MarsPhotoSources>) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<MarsPhotoSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
                .flatMap { mapper.Flowable(it) }
                .subscribe({ response ->
                    Log.d(TAG, "On Next Called")
                    mNews.value = Data(responseType = Status.SUCCESSFUL, data = response)
                }, { error ->
                    Log.d(TAG, "On Error Called")
                    mNews.value = Data(responseType = Status.ERROR, error = Error(error.message))
                }, {
                    Log.d(TAG, "On Complete Called")
                })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews
}