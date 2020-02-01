package sample.nackun.com.studyfirst.data.upbit

class CoinOneRepositoryImpl(
    private val remoteDataSource: CoinOneDataSource
) : CoinOneRepository {
    override fun requestAllTicker() =
        remoteDataSource.requestAllTicker()
}