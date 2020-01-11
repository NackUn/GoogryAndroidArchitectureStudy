package sample.nackun.com.studyfirst.data.bithumb

import io.reactivex.Single
import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbRepository {
    fun requestAllTicker(): Single<BithumbResult>
}