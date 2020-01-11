package sample.nackun.com.studyfirst.vo


import com.google.gson.annotations.SerializedName

data class BithumbData(
    @SerializedName("BithumbTicker")
    val bithumbTickers: List<BithumbTicker>
)