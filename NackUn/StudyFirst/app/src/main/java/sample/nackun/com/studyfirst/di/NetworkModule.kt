package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.network.UpbitApi

fun getNetworkModule(baseUrl: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
            .create(UpbitApi::class.java)
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }
}