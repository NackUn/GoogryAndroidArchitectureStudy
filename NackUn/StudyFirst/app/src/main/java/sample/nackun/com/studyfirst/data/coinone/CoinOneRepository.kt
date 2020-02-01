package sample.nackun.com.studyfirst.data.upbit

import sample.nackun.com.studyfirst.vo.CoinOneTicker

interface CoinOneRepository {
    suspend fun requestAllTicker(): Map<String, Any>
    suspend fun requestTicker(currency: String): CoinOneTicker
}