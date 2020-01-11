package sample.nackun.com.studyfirst.network

import io.reactivex.Single
import retrofit2.http.GET
import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbApi {
    @GET("public/ticker/ALL")
    fun requestAllTicker(): Single<BithumbResult>
}