package sample.nackun.com.studyfirst.domain

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.vo.CoinOneTicker

class GetCoinOneTickersUseCase(private val repository: CoinOneRepository) {
    suspend operator fun invoke(): List<CoinOneTicker> =
        repository.requestAllTicker().filter {
            !(it.key == "result" || it.key == "errorCode" || it.key == "timestamp")
        }.map {
            val gson = Gson()
            gson.fromJson(it.value.toString(), CoinOneTicker::class.java)
        }
}