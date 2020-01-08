package sample.nackun.com.studyfirst.data

class RepositoryImpl(
    private val remoteDataSource: DataSource
) : Repository {
    override fun requestMarket() =
        remoteDataSource.requestMarket()

    override fun requestTicker(markets: String) =
        remoteDataSource.requestTicker(markets)
}