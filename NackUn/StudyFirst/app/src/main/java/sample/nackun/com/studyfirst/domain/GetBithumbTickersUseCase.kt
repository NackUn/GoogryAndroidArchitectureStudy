package sample.nackun.com.studyfirst.domain

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.vo.BithumbTicker

class GetBithumbTickersUseCase(private val repository: BithumbRepository) {
    suspend operator fun invoke(): List<BithumbTicker> =
        repository.requestAllTicker().let {
            it.bithumbData.filterKeys {
                !it.equals("date")
            }.map { bithumbTicker ->
                val gson = Gson()
                gson.fromJson(bithumbTicker.value.toString(), BithumbTicker::class.java).apply {
                    setMarket(bithumbTicker.key)
                }
            }
        }
}