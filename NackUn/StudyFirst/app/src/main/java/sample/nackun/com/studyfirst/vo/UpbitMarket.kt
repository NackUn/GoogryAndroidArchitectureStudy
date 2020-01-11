package sample.nackun.com.studyfirst.vo

import com.google.gson.annotations.SerializedName

data class UpbitMarket(
    @SerializedName("market")
    val market: String
)