package sample.nackun.com.studyfirst.network

import retrofit2.http.GET
import retrofit2.http.Path
import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbApi {
    @GET("public/ticker/ALL")
    suspend fun requestAllTicker(): BithumbResult

    @GET("public/ticker/{currency}")
    suspend fun requestTicker(@Path("currency") currency: String): BithumbResult
}