package sample.nackun.com.studyfirst.data.upbit

interface CoinOneDataSource {
    suspend fun requestAllTicker(): Map<String, Any>
}