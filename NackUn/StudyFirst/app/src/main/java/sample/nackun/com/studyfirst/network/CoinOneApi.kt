package sample.nackun.com.studyfirst.network

import io.reactivex.Single
import retrofit2.http.GET

interface CoinOneApi {
    @GET("/ticker?currency=all")
    fun requestAllTicker(): Single<Map<String, Any>>
}