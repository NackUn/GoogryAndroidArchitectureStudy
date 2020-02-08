package sample.nackun.com.studyfirst.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.domain.GetBithumbTickerUseCase
import sample.nackun.com.studyfirst.domain.GetCoinOneTickerUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitTickersUseCase
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class DetailViewModel(
    private val tickerName: String,
    private val getUpbitTickersUseCase: GetUpbitTickersUseCase,
    private val getBithumbTickerUseCase: GetBithumbTickerUseCase,
    private val getCoinOneTickerUseCase: GetCoinOneTickerUseCase
) : BaseViewModel() {
    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

    init {
        _tickers.value = mutableListOf()
    }

    private fun onError(t: Throwable) {
        _errMsg.value = t
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun showTickers() {
        viewModelScope.launch {
            val tickers: MutableList<Ticker> = mutableListOf()

            try {
                val upbitTickers = async { getUpbitTickersUseCase("KRW-" + tickerName) }
                val upbitTicker = upbitTickers.await().last()
                tickers.add(TickerFormatter.toTicker(upbitTicker))
            } catch (cause: Throwable) {

            }

            try {
                val bithumbTicker = async { getBithumbTickerUseCase(tickerName) }
                tickers.add(TickerFormatter.toTicker(bithumbTicker.await()))
            } catch (cause: Throwable) {

            }

            try {
                val coinOneTicker = async { getCoinOneTickerUseCase(tickerName) }
                tickers.add(TickerFormatter.toTicker(coinOneTicker.await()))
            } catch (cause: Throwable) {

            }
            onTickersLoaded(tickers)
        }
    }
}