package sample.nackun.com.studyfirst.ui.ticker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.data.upbit.UpbitRepository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.BithumbTicker
import sample.nackun.com.studyfirst.vo.Ticker
import sample.nackun.com.studyfirst.vo.UpbitTicker

class TickerViewModel(
    private val upbitRepository: UpbitRepository,
    private val bithumbRepository: BithumbRepository
) : BaseViewModel() {

    private val firstMarketName = "KRW"

    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _selectedMarket = MutableLiveData<String>()
    val selectedMarket: LiveData<String> get() = _selectedMarket
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

    private val bithumbTickers = mutableListOf<BithumbTicker>()

    init {
        _tickers.value = mutableListOf()
        _selectedMarket.value = firstMarketName
    }

    private fun onError(t: Throwable) {
        _errMsg.value = t
    }

    private fun toTickers(upbitTickers: List<UpbitTicker>, bithumbTickers: List<BithumbTicker>) {
        onTickersLoaded(TickerFormatter.combine(upbitTickers, bithumbTickers))
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun selectedMarket(marketLike: String) {
        _selectedMarket.value = marketLike
        showTickers(marketLike)
    }

    fun showTickers(marketLike: String?) {
        marketLike?.let {
            if (it.equals("KRW")) {
                addDisposable(
                    bithumbRepository.requestAllTicker()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                it.bithumbData.filterKeys {
                                    !it.equals("date")
                                }.let {
                                    val gson = Gson()
                                    for ((key, value) in it) {
                                        val bithumbTicker = gson.fromJson(value.toString(), BithumbTicker::class.java)
                                        bithumbTicker.setMarket(key)
                                        bithumbTickers.add(bithumbTicker)
                                    }
                                }
                            },
                            {
                                Log.e("aa12", it.toString())
                            }
                        )
                )
            }
            addDisposable(
                upbitRepository.requestMarket()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap { data ->
                        upbitRepository.requestTicker(data.filter {
                            it.market.startsWith(marketLike)
                        }.joinToString { it.market })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                    }.subscribe(
                        {
                            toTickers(it, bithumbTickers)
                        },
                        { Log.e("aa12", it.toString()) }
                    )
            )
        } ?: onError(IllegalStateException("Selected UpbitMarket is not exist"))
    }
}