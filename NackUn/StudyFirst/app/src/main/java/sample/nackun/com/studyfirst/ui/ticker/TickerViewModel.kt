package sample.nackun.com.studyfirst.ui.ticker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.domain.GetBithumbTickersUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitMarketUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitTickersUseCase
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.BithumbTicker
import sample.nackun.com.studyfirst.vo.Ticker
import sample.nackun.com.studyfirst.vo.UpbitTicker

class TickerViewModel(
    private val getUpbitMarketUseCase: GetUpbitMarketUseCase,
    private val getUpbitTickersUseCase: GetUpbitTickersUseCase,
    private val getBithumbTickersUseCase: GetBithumbTickersUseCase
) : BaseViewModel() {

    private val firstMarketName = "KRW"

    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _selectedMarket = MutableLiveData<String>()
    val selectedMarket: LiveData<String> get() = _selectedMarket
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

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
                    getBithumbTickersUseCase()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .zipWith(
                            getUpbitMarketUseCase()
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .flatMap { data ->
                                    getUpbitTickersUseCase(data.filter {
                                        it.market.startsWith(marketLike)
                                    }.joinToString { it.market })
                                        .subscribeOn(Schedulers.computation())
                                        .observeOn(AndroidSchedulers.mainThread())
                                },
                            BiFunction { t1: List<BithumbTicker>, t2: List<UpbitTicker> -> toTickers(t2, t1) }
                        )
                        .subscribe()
                )
            }
        } ?: onError(IllegalStateException("Selected UpbitMarket is not exist"))
    }
}