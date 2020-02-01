package sample.nackun.com.studyfirst.data.bithumb

import sample.nackun.com.studyfirst.vo.BithumbResult

interface BithumbDataSource {
    suspend fun requestAllTicker(): BithumbResult
}