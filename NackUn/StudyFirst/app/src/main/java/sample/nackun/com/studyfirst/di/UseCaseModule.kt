package sample.nackun.com.studyfirst.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.domain.*

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

    single {
        GetBithumbTickerUseCase(get())
    }

    single {
        GetCoinOneTickerUseCase(get())
    }
}