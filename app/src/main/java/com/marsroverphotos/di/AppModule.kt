package com.marsroverphotos.di

import androidx.room.Room
import com.module.data.api.RemoteNewsApi
import com.module.data.db.NewsDatabase
import com.module.data.entities.NewsDataEntityMapper
import com.module.data.entities.NewsEntityDataMapper
import com.module.data.repository.NewsCacheImpl
import com.module.domain.usecases.GetNewsUseCase
import com.module.data.repository.NewsRemoteImpl
import com.module.data.repository.NewsRepositoryImpl
import com.module.domain.repositories.NewsRepository
import com.marsroverphotos.common.AsyncFlowableTransformer
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import com.marsroverphotos.news.NewsViewModel
import com.marsroverphotos.mappers.NewsEntityMapper
import org.koin.android.ext.koin.androidApplication
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { NewsRemoteImpl(api = get(API))}
    single(name = "local") {
        NewsCacheImpl(database = get(DATABASE), entityToDataMapper = NewsEntityDataMapper(),
                dataToEntityMapper = NewsDataEntityMapper())
    }
    single { NewsRepositoryImpl(remote = get("remote"), cache = get("local")) as NewsRepository }
}

val mUseCaseModules = module {
    factory(name = "getNewsUseCase") { GetNewsUseCase(transformer = AsyncFlowableTransformer(), repositories = get()) }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNewsApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) { Room.databaseBuilder(androidApplication(), NewsDatabase::class.java, "news_articles").build() }
}

val mViewModels = module {
    viewModel {
        NewsViewModel(getNewsUseCase = get(GET_NEWS_USECASE), mapper = NewsEntityMapper())
    }
}

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"