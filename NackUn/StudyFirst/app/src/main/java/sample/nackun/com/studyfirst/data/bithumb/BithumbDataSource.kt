package sample.nackun.com.studyfirst.data.bithumb

import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbDataSource {
    suspend fun requestAllTicker(): BithumbResult
    suspend fun requestTicker(currency: String): BithumbResult
}