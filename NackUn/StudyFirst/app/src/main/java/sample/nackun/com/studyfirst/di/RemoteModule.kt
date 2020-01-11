package sample.nackun.com.studyfirst.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import sample.nackun.com.studyfirst.data.bithumb.BithumbDataSource
import sample.nackun.com.studyfirst.data.bithumb.remote.BithumbRemoteDataSource
import sample.nackun.com.studyfirst.data.upbit.UpbitDataSource
import sample.nackun.com.studyfirst.data.upbit.remote.UpbitRemoteDataSource
import sample.nackun.com.studyfirst.network.BithumbApi
import sample.nackun.com.studyfirst.network.UpbitApi

val remoteModule = module {
    single<UpbitApi> { get<Retrofit> { parametersOf("https://api.upbit.com") }.create(UpbitApi::class.java) }
    single<BithumbApi> { get<Retrofit> { parametersOf("https://api.bithumb.com") }.create(BithumbApi::class.java) }
    single<UpbitDataSource> { UpbitRemoteDataSource(get()) }
    single<BithumbDataSource> { BithumbRemoteDataSource(get()) }
}