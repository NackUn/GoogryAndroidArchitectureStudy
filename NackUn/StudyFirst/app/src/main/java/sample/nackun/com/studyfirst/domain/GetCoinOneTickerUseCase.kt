package sample.nackun.com.studyfirst.domain

import sample.nackun.com.studyfirst.data.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.vo.CoinOneTicker

class GetCoinOneTickerUseCase(private val repository: CoinOneRepository) {
    suspend operator fun invoke(currency: String): CoinOneTicker =
        repository.requestTicker(currency)
}