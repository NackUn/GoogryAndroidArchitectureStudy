package sample.nackun.com.studyfirst.data.upbit

import io.reactivex.Single

interface CoinOneDataSource {
    fun requestAllTicker(): Single<Map<String, Any>>
}