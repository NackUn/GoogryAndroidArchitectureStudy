package sample.nackun.com.studyfirst.network

import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.vo.CoinOneTicker

interface CoinOneApi {
    @GET("/ticker?currency=all")
    suspend fun requestAllTicker(): Map<String, Any>

    @GET("/ticker")
    suspend fun requestTicker(@Query("currency") currency: String): CoinOneTicker
}