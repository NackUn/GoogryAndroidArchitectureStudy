package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.domain.GetBithumbTickersUseCase
import sample.nackun.com.studyfirst.domain.GetCoinOneTickersUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitMarketUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitTickersUseCase

val useCaseModule = module {
    single {
        GetUpbitMarketUseCase(get())
    }

    single {
        GetUpbitTickersUseCase(get())
    }

    single {
        GetBithumbTickersUseCase(get())
    }

    single {
        GetCoinOneTickersUseCase(get())
    }
}