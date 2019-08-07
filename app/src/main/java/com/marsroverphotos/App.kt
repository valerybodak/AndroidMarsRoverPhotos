package com.marsroverphotos

import android.app.Application
import com.marsroverphotos.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
                listOf(mNetworkModules,
                        mViewModels,
                        mRepositoryModules,
                        mUseCaseModules,
                        mLocalModules)

        )
    }
}