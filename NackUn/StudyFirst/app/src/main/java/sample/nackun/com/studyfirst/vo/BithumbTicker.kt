package sample.nackun.com.studyfirst.vo


import com.google.gson.annotations.SerializedName

data class BithumbTicker(
    //최근 24시간 거래금액
    @SerializedName("acc_trade_value_24H")
    val accTradeValue24H: String,
    //최근 24시간 변동가
    @SerializedName("fluctate_24H")
    val fluctate24H: String,
    //전일종가
    @SerializedName("prev_closing_price")
    val prevClosingPrice: String,
    //종가 00시 기준
    @SerializedName("closing_price")
    val closingPrice: String,

    @SerializedName("acc_trade_value")
    val accTradeValue: String,
    @SerializedName("fluctate_rate_24H")
    val fluctateRate24H: String,
    @SerializedName("max_price")
    val maxPrice: String,
    @SerializedName("min_price")
    val minPrice: String,
    @SerializedName("opening_price")
    val openingPrice: String,
    @SerializedName("units_traded")
    val unitsTraded: String,
    @SerializedName("units_traded_24H")
    val unitsTraded24H: String
)