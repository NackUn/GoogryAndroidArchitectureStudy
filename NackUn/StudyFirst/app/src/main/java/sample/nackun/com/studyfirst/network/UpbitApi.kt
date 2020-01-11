package sample.nackun.com.studyfirst.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.vo.UpbitMarket
import sample.nackun.com.studyfirst.vo.UpbitTicker

interface UpbitApi {
    @GET("v1/market/all")
    fun requestMarket(): Single<List<UpbitMarket>>

    @GET("v1/ticker/")
    fun requestTicker(@Query("markets") markets: String): Single<List<UpbitTicker>>
}