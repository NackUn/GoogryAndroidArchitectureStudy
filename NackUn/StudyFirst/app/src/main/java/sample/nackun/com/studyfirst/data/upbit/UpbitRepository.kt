package sample.nackun.com.studyfirst.data.upbit

import io.reactivex.Single
import sample.nackun.com.studyfirst.vo.UpbitMarket
import sample.nackun.com.studyfirst.vo.UpbitTicker

interface UpbitRepository {
    fun requestMarket(): Single<List<UpbitMarket>>
    fun requestTicker(markets: String): Single<List<UpbitTicker>>
}