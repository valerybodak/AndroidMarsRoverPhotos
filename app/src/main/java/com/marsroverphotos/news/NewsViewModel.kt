package com.marsroverphotos.news

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.module.domain.common.Mapper
import com.module.domain.entities.NewsSourcesEntity
import com.module.domain.usecases.GetNewsUseCase
import com.marsroverphotos.common.BaseViewModel
import com.marsroverphotos.entities.Data
import com.marsroverphotos.entities.Error
import com.marsroverphotos.entities.NewsSources
import com.marsroverphotos.entities.Status

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase,
                    private val mapper: Mapper<NewsSourcesEntity, NewsSources>) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

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