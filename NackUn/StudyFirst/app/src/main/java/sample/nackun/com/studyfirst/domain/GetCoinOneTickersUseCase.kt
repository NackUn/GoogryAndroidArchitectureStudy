package sample.nackun.com.studyfirst.domain

import android.util.Log
import com.google.gson.Gson
import io.reactivex.Single
import sample.nackun.com.studyfirst.data.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.vo.CoinOneTicker

class GetCoinOneTickersUseCase(private val repository: CoinOneRepository) {
    operator fun invoke(): Single<List<CoinOneTicker>> =
        repository.requestAllTicker().map {
            Log.i("aa12", it.toString())
            val gson = Gson()
            it.filterKeys {
                !(it.equals("result") || it.equals("errorCode") || it.equals("timestamp"))
            }.map {
                Log.i("aa12", it.value.toString())
                gson.fromJson(it.value.toString(), CoinOneTicker::class.java)
            }
        }
}