package sample.nackun.com.studyfirst.domain

import io.reactivex.Single
import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.vo.UpbitMarket

class GetUpbitMarketUseCase(private val repository: UpbitRepository) {
    operator fun invoke(): Single<List<UpbitMarket>> = repository.requestMarket()
}