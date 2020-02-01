package sample.nackun.com.studyfirst.data.upbit

interface CoinOneRepository {
    suspend fun requestAllTicker(): Map<String, Any>
}