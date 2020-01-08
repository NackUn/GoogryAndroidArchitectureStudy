package sample.nackun.com.studyfirst.data

import io.reactivex.Single
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

interface DataSource {
    fun requestMarket(): Single<List<Market>>
    fun requestTicker(markets: String): Single<List<Ticker>>
}