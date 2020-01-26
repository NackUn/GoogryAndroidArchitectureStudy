package sample.nackun.com.studyfirst.vo

import com.google.gson.annotations.SerializedName


data class BithumbTicker(
//    최근 24시간 거래금액 = "acc_trade_value_24H"
    @SerializedName("acc_trade_value_24H")
    val accTradeValue24H: Double,
//    최근 24시간 변동가 ="fluctate_24H"
    @SerializedName("fluctate_24H")
    val fluctate24H: Double,
//    종목 구분 코드 = "market"
    private var market: String,
//    전일종가 = "prev_closing_price"
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
//    종가 00시 기준 = "closing_price"
    @SerializedName("closing_price")
    val closingPrice: Double
) {
    fun setMarket(market: String) {
        this.market = market
    }

    fun getMarket() = market
}