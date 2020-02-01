package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepositoryImpl
import sample.nackun.com.studyfirst.data.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.data.upbit.CoinOneRepositoryImpl
import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.data.upbit.UpbitRepositoryImpl

val repositoryModule = module {
    single<UpbitRepository> {
        UpbitRepositoryImpl(
            get()
        )
    }
    single<BithumbRepository> {
        BithumbRepositoryImpl(
            get()
        )
    }
    single<CoinOneRepository> {
        CoinOneRepositoryImpl(
            get()
        )
    }
}