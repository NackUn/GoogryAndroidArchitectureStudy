package sample.nackun.com.studyfirst.data.upbit

class UpbitRepositoryImpl(
    private val remoteDataSource: UpbitDataSource
) : UpbitRepository {
    override fun requestMarket() =
        remoteDataSource.requestMarket()

    override fun requestTicker(markets: String) =
        remoteDataSource.requestTicker(markets)
}