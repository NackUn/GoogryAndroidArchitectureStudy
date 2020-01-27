package sample.nackun.com.studyfirst.domain

import io.reactivex.Single
import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.vo.UpbitTicker

class GetUpbitTickersUseCase(private val repository: UpbitRepository) {
    operator fun invoke(market: String): Single<List<UpbitTicker>> = repository.requestTicker(market)
}