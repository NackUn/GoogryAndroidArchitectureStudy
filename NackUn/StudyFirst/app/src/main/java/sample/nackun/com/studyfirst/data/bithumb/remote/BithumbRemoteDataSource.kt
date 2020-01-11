package sample.nackun.com.studyfirst.data.bithumb.remote

import io.reactivex.Single
import sample.nackun.com.studyfirst.data.bithumb.BithumbDataSource
import sample.nackun.com.studyfirst.network.BithumbApi
import sample.nackun.com.studyfirst.vo.BithumbResult

class BithumbRemoteDataSource(
    private val retrofitService: BithumbApi
) : BithumbDataSource {
    override fun requestAllTicker(): Single<BithumbResult> =
        retrofitService.requestAllTicker()
}