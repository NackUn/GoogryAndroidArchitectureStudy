package sample.nackun.com.studyfirst.domain

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.vo.BithumbTicker

class GetBithumbTickerUseCase(private val repository: BithumbRepository) {
    suspend operator fun invoke(currency: String): BithumbTicker =
        repository.requestTicker(currency).let {
            val gson = Gson()
            gson.fromJson(it.bithumbData.toString(), BithumbTicker::class.java).apply {
                setMarket(currency)
            }
        }
}