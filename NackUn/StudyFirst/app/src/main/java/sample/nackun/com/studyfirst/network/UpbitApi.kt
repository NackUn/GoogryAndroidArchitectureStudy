package sample.nackun.com.studyfirst.network

import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.vo.UpbitMarket
import sample.nackun.com.studyfirst.vo.UpbitTicker

interface UpbitApi {
    @GET("v1/market/all")
    suspend fun requestMarket(): List<UpbitMarket>

    @GET("v1/ticker/")
    suspend fun requestTicker(@Query("markets") markets: String): List<UpbitTicker>
}