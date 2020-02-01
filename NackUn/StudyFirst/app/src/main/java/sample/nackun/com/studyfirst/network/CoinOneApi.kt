package sample.nackun.com.studyfirst.network

import retrofit2.http.GET

interface CoinOneApi {
    @GET("/ticker?currency=all")
    suspend fun requestAllTicker(): Map<String, Any>
}