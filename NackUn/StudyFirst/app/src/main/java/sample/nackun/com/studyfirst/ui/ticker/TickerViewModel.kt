package sample.nackun.com.studyfirst.ui.ticker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.domain.GetBithumbTickersUseCase
import sample.nackun.com.studyfirst.domain.GetCoinOneTickersUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitMarketUseCase
import sample.nackun.com.studyfirst.domain.GetUpbitTickersUseCase
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.BithumbTicker
import sample.nackun.com.studyfirst.vo.CoinOneTicker
import sample.nackun.com.studyfirst.vo.Ticker
import sample.nackun.com.studyfirst.vo.UpbitTicker

class TickerViewModel(
    private val getUpbitMarketUseCase: GetUpbitMarketUseCase,
    private val getUpbitTickersUseCase: GetUpbitTickersUseCase,
    private val getBithumbTickersUseCase: GetBithumbTickersUseCase,
    private val getCoinOneTickersUseCase: GetCoinOneTickersUseCase
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

    private fun toTickers(
        upbitTickers: List<UpbitTicker>,
        bithumbTickers: List<BithumbTicker>,
        coinOneTickers: List<CoinOneTicker>
    ) {
        onTickersLoaded(TickerFormatter.combine(upbitTickers, bithumbTickers, coinOneTickers))
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun selectedMarket(marketLike: String) {
        _selectedMarket.value = marketLike
        showTickers(marketLike)
    }

    fun showTickers(marketLike: String?) {
        viewModelScope.launch {
            val upbitMarket = getUpbitMarketUseCase()
            val strUpbitMarket = upbitMarket.filter {
                it.market.startsWith(marketLike ?: "KRW")
            }.joinToString { it.market }
            val upbitTickers = async { getUpbitTickersUseCase(strUpbitMarket) }

            val bithumbTickers = async { getBithumbTickersUseCase() }

            val coinOneTickers = async { getCoinOneTickersUseCase() }

            toTickers(upbitTickers.await(), bithumbTickers.await(), coinOneTickers.await())
        }
    }
}