package sample.nackun.com.studyfirst.vo


import com.google.gson.annotations.SerializedName

data class UpbitTicker(
    //24시간 누적 거래대금
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double,
    //변화액의 절대값
    @SerializedName("change_price")
    val changePrice: Double,
    //종목 구분 코드
    @SerializedName("market")
    val market: String,
    //전일 종가
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    //종가
    @SerializedName("trade_price")
    val tradePrice: Double
)