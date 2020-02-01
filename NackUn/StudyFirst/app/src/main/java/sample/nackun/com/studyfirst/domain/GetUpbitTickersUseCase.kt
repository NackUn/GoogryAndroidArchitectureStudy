package sample.nackun.com.studyfirst.domain

import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.vo.UpbitTicker

class GetUpbitTickersUseCase(private val repository: UpbitRepository) {
    suspend operator fun invoke(market: String): List<UpbitTicker> = repository.requestTicker(market)
}