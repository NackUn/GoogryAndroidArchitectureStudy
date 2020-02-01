package sample.nackun.com.studyfirst.network

import retrofit2.http.GET
import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbApi {
    @GET("public/ticker/ALL")
    suspend fun requestAllTicker(): BithumbResult
}