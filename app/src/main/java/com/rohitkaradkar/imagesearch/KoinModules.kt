package com.rohitkaradkar.imagesearch

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.rohitkaradkar.imagesearch.remote.RemoteImageDataSource
import com.rohitkaradkar.imagesearch.remote.WikiImageSearchService
import com.rohitkaradkar.imagesearch.ui.SearchViewModel
import com.rohitkaradkar.imagesearch.utils.AppConstants
import com.rohitkaradkar.imagesearch.utils.DependencyKeys
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Dependencies for app
 * specific module can be loaded during test
 */


val androidModule = module {
    // provides cache dir to okhttp
    factory<File>(DependencyKeys.cacheDirectory) { androidContext().cacheDir }

    factory(DependencyKeys.debug) {
        return@factory BuildConfig.DEBUG
    }
}

// provides retrofit
val retrofitModule = module {

    // rx java call for retrofit
    single { RxJava2CallAdapterFactory.create() }

    // converter
    single<JacksonConverterFactory> { JacksonConverterFactory.create() }

    // okhttp cache
    single {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return@single Cache(get<File>(DependencyKeys.cacheDirectory), cacheSize.toLong())
    }

    // Http logging
    single {
        val interceptor = HttpLoggingInterceptor()
        if (get<Boolean>(DependencyKeys.debug)) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return@single interceptor
    }

    // okhttp client
    single<OkHttpClient> {
        val client = OkHttpClient.Builder()
            .cache(get()) // get Cache
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        return@single client.build()
    }

    // this url can be overriden by mockWebServer url during test
    single<HttpUrl>() {
        HttpUrl.parse(AppConstants.wikipediaBaseUrl)!!
    }

    // retrofit
    single<Retrofit> {
        Retrofit.Builder().baseUrl(get<HttpUrl>())
            .addConverterFactory(get<JacksonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }
}

// provides data source for image search
val remoteDataModule = module {
    single<WikiImageSearchService> {
        get<Retrofit>().create(WikiImageSearchService::class.java)
    }

    single<RemoteImageDataSource> {
        RemoteImageDataSource(get<WikiImageSearchService>())
    }
}

// provides viewModel
val searchActivityModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(get<RemoteImageDataSource>())
    }
}