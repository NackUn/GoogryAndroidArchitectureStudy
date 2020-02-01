package sample.nackun.com.studyfirst.data.upbit.remote

import sample.nackun.com.studyfirst.data.upbit.UpbitDataSource
import sample.nackun.com.studyfirst.network.UpbitApi

class UpbitRemoteDataSource(
    private val retrofitService: UpbitApi
) : UpbitDataSource {
    override suspend fun requestMarket() =
        retrofitService.requestMarket()

    override suspend fun requestTicker(markets: String) =
        retrofitService.requestTicker(markets)
}