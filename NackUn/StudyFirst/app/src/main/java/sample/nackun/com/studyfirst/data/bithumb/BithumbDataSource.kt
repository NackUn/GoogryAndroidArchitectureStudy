package sample.nackun.com.studyfirst.data.bithumb

import io.reactivex.Single
import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbDataSource {
    fun requestAllTicker(): Single<BithumbResult>
}