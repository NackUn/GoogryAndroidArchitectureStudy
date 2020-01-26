package sample.nackun.com.studyfirst.util

import android.graphics.Color
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.vo.BithumbTicker
import sample.nackun.com.studyfirst.vo.Ticker
import sample.nackun.com.studyfirst.vo.UpbitTicker

object TickerFormatter {
    fun combine(
        upbitTickers: List<UpbitTicker>,
        bithumbTickers: List<BithumbTicker>
    ): List<Ticker> {
        val combineList = mutableListOf<Ticker>()
        combineList.clear()

        upbitTickers.forEach { upbitTicker ->
            bithumbTickers.find { bithumbTicker ->
                upbitTicker.market.split("-")[1].equals(bithumbTicker.getMarket())
            }?.let { bithumbTicker ->
                if (bithumbTicker.accTradeValue24H > upbitTicker.accTradePrice24h) {
                    combineList.add(
                        toTicker(bithumbTicker)
                    )
                } else {
                    combineList.add(
                        toTicker(upbitTicker)
                    )
                }
            }
        }
        return combineList
    }

    fun toTicker(upbitTicker: UpbitTicker): Ticker = Ticker(
        upbitTicker.accTradePrice24h,
        upbitTicker.changePrice,
        upbitTicker.market,
        upbitTicker.prevClosingPrice,
        upbitTicker.tradePrice,
        R.drawable.upbit_img
    )

    fun toTicker(bithumbTicker: BithumbTicker): Ticker = Ticker(
        bithumbTicker.accTradeValue24H,
        bithumbTicker.fluctate24H,
        bithumbTicker.getMarket(),
        bithumbTicker.prevClosingPrice,
        bithumbTicker.closingPrice,
        R.drawable.bithumb_img
    )

    fun convertTo(target: List<Ticker>): List<Map<String, String>> {
        val convertList = mutableListOf<Map<String, String>>()
        convertList.clear()

        target.forEachIndexed { index, ticker ->
            convertList.add(
                index,
                hashMapOf(
                    "tickerName" to getTickerName(ticker.market),
                    "currentPrice" to getCurrentPrice(ticker.tradePrice),
                    "comparePrice" to getComparePrice(ticker.tradePrice, ticker.prevClosingPrice),
                    "compareColor" to getCompareColor(ticker.tradePrice, ticker.prevClosingPrice),
                    "changePrice" to getChangePrice(ticker.accTradePrice24h),
                    "exchangeImg" to getMarketImgSrc(ticker.exchangeImg)
                )
            )
        }
        return convertList
    }

    private fun getMarketImgSrc(exchangeImg: Int) =
        exchangeImg.toString()

    private fun getTickerName(tickerName: String) =
        tickerName.substring(tickerName.indexOf("-") + 1, tickerName.length)

    private fun getCurrentPrice(currentPrice: Double): String {
        return when {
            currentPrice > 10 -> String.format("%,d", currentPrice.toInt())
            currentPrice > 1 -> String.format("%,.2f", currentPrice)
            currentPrice < 1 -> String.format("%,.8f", currentPrice)
            else -> String.format("%,d", currentPrice.toInt())
        }
    }

    private fun getComparePrice(currentPrice: Double, beforePrice: Double) =
        String.format("%.2f", ((currentPrice - beforePrice) / beforePrice * 100)) + "%"

    private fun getCompareColor(currentPrice: Double, beforePrice: Double): String {
        val differPrice = (currentPrice - beforePrice) / beforePrice * 100
        return when {
            differPrice > 0 -> Color.RED.toString()
            differPrice < 0 -> Color.BLUE.toString()
            else -> Color.BLACK.toString()
        }
    }

    private fun getChangePrice(changePrice: Double): String {
        return when {
            changePrice / 1_000_000 > 50 ->
                String.format("%,d", (changePrice / 1_000_000).toInt()) + " M"
            changePrice / 1_000 > 1_000 ->
                String.format("%,d", (changePrice / 1_000).toInt()) + " K"
            changePrice > 1_000 ->
                String.format("%,d", changePrice.toInt())
            else ->
                String.format("%,.3f", changePrice)
        }
    }
}