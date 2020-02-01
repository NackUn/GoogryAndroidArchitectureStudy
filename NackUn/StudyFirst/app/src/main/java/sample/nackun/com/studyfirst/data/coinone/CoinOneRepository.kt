package sample.nackun.com.studyfirst.data.upbit

import io.reactivex.Single

interface CoinOneRepository {
    fun requestAllTicker(): Single<Map<String, Any>>
}