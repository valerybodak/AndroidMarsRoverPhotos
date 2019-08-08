package com.marsroverphotos.di

import androidx.room.Room
import com.module.data.api.RemoteNasaApi
import com.module.data.db.AppDatabase
import com.module.data.entities.MarsPhotoDataEntityMapper
import com.module.data.entities.NewsEntityDataMapper
import com.module.data.repository.MarsPhotoCacheImpl
import com.module.domain.usecases.GetMarsPhotosUseCase
import com.module.data.repository.MarsPhotoRemoteImpl
import com.module.data.repository.MarsPhotoRepositoryImpl
import com.module.domain.repositories.MarsPhotoRepository
import com.marsroverphotos.common.AsyncFlowableTransformer
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import com.marsroverphotos.news.NewsViewModel
import com.marsroverphotos.mappers.MarsPhotoEntityMapper
import org.koin.android.ext.koin.androidApplication
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { MarsPhotoRemoteImpl(api = get(API))}
    single(name = "local") {
        MarsPhotoCacheImpl(database = get(DATABASE), entityToDataMapper = NewsEntityDataMapper(),
                dataToEntityMapper = MarsPhotoDataEntityMapper())
    }
    single { MarsPhotoRepositoryImpl(remote = get("remote"), cache = get("local")) as MarsPhotoRepository }
}

val mUseCaseModules = module {
    factory(name = "getNewsUseCase") { GetMarsPhotosUseCase(transformer = AsyncFlowableTransformer(), repositories = get()) }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNasaApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "news_articles").build() }
}

val mViewModels = module {
    viewModel {
        NewsViewModel(getNewsUseCase = get(GET_NEWS_USECASE), mapper = MarsPhotoEntityMapper())
    }
}

private const val BASE_URL = "https://api.nasa.gov/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"