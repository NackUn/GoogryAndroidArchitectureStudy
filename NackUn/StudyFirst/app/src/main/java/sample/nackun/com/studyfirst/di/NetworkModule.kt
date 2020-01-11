package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { (baseUrl: String) ->
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }
}