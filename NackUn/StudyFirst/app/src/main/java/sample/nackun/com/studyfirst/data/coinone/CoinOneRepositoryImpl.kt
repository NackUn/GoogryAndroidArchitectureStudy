package sample.nackun.com.studyfirst.data.upbit

import sample.nackun.com.studyfirst.vo.CoinOneTicker

class CoinOneRepositoryImpl(
    private val remoteDataSource: CoinOneDataSource
) : CoinOneRepository {
    override suspend fun requestTicker(currency: String): CoinOneTicker =
        remoteDataSource.requestTicker(currency)

    override suspend fun requestAllTicker() =
        remoteDataSource.requestAllTicker()
}