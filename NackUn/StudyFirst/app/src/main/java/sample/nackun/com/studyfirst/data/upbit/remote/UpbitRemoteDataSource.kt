package sample.nackun.com.studyfirst.data.upbit.remote

import io.reactivex.Single
import sample.nackun.com.studyfirst.data.upbit.UpbitDataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.UpbitMarket
import sample.nackun.com.studyfirst.vo.UpbitTicker

class UpbitRemoteDataSource(
    private val retrofitService: UpbitApi
) : UpbitDataSource {
    override fun requestMarket(): Single<List<UpbitMarket>> =
        retrofitService.requestMarket()

    override fun requestTicker(markets: String): Single<List<UpbitTicker>> =
        retrofitService.requestTicker(markets)
}