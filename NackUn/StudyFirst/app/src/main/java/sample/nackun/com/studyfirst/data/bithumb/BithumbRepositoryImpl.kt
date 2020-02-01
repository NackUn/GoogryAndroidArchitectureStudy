package sample.nackun.com.studyfirst.data.bithumb

class BithumbRepositoryImpl(
    private val remoteDataSource: BithumbDataSource
) : BithumbRepository {
    override suspend fun requestTicker(currency: String) =
        remoteDataSource.requestTicker(currency)

    override suspend fun requestAllTicker() =
        remoteDataSource.requestAllTicker()
}