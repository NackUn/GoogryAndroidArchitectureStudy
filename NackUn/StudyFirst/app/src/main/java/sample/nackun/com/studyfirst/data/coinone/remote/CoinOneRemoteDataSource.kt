package sample.nackun.com.studyfirst.data.coinone.remote

import sample.nackun.com.studyfirst.data.upbit.CoinOneDataSource
import sample.nackun.com.studyfirst.network.CoinOneApi

class CoinOneRemoteDataSource(
    private val retrofitService: CoinOneApi
) : CoinOneDataSource {
    override suspend fun requestAllTicker() =
        retrofitService.requestAllTicker()
}