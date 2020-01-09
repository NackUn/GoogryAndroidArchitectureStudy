package sample.nackun.com.studyfirst.ui.ticker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerViewModel(
    private val repository: Repository
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

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun selectedMarket(marketLike: String) {
        _selectedMarket.value = marketLike
        showTickers(marketLike)
    }

    fun showTickers(marketLike: String?) {
        marketLike?.let {
            addDisposable(
                repository.requestMarket()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap { data ->
                        repository.requestTicker(data.filter {
                            it.market.startsWith(marketLike)
                        }.joinToString { it.market })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                    }.subscribe(
                        { onTickersLoaded(it) },
                        { it }
                    )
            )
        } ?: onError(IllegalStateException("Selected Market is not exist"))
    }
}