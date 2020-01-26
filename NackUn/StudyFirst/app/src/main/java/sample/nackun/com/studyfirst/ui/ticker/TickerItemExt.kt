package sample.nackun.com.studyfirst.ui.ticker

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImg")
fun ImageView.setImg(src: Int) {
    this.setImageResource(src)
}