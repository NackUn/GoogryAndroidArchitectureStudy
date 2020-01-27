package sample.nackun.com.studyfirst.domain

import com.google.gson.Gson
import io.reactivex.Single
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.vo.BithumbTicker

class GetBithumbTickersUseCase(private val repository: BithumbRepository) {
    operator fun invoke(): Single<List<BithumbTicker>> =
        repository.requestAllTicker().map {
            val gson = Gson()
            it.bithumbData.filterKeys {
                !it.equals("date")
            }.map { bithumbTicker ->
                gson.fromJson(bithumbTicker.value.toString(), BithumbTicker::class.java).apply {
                    setMarket(bithumbTicker.key)
                }
            }
        }

}