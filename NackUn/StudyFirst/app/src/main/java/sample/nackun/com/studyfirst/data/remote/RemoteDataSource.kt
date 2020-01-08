package sample.nackun.com.studyfirst.data.remote

import io.reactivex.Single
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

class RemoteDataSource(
    private val retrofitService: UpbitApi
) : DataSource {
    override fun requestMarket(): Single<List<Market>> =
        retrofitService.requestMarket()

    override fun requestTicker(markets: String): Single<List<Ticker>> =
        retrofitService.requestTicker(markets)
}