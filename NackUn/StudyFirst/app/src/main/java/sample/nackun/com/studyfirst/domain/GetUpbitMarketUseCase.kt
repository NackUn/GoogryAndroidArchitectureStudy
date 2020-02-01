package sample.nackun.com.studyfirst.domain

import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.vo.UpbitMarket

class GetUpbitMarketUseCase(private val repository: UpbitRepository) {
    suspend operator fun invoke(): List<UpbitMarket> = repository.requestMarket()
}