package sample.nackun.com.studyfirst.data.upbit

class UpbitRepositoryImpl(
    private val remoteDataSource: UpbitDataSource
) : UpbitRepository {
    override suspend fun requestMarket() =
        remoteDataSource.requestMarket()

    override suspend fun requestTicker(markets: String) =
        remoteDataSource.requestTicker(markets)
}