package sample.nackun.com.studyfirst.data.bithumb

import io.reactivex.Single
import sample.nackun.com.studyfirst.vo.BithumbResult

class BithumbRepositoryImpl(
    private val remoteDataSource: BithumbDataSource
) : BithumbRepository {
    override fun requestAllTicker(): Single<BithumbResult> =
        remoteDataSource.requestAllTicker()
}