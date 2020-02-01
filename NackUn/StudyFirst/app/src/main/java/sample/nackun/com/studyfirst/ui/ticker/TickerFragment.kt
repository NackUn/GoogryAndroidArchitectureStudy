package sample.nackun.com.studyfirst.ui.ticker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.ticker_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.databinding.TickerFragmentBinding
import sample.nackun.com.studyfirst.databinding.TickerItemBinding
import sample.nackun.com.studyfirst.ui.detail.DetailActivity
import sample.nackun.com.studyfirst.util.ClickAdapter

class TickerFragment : BaseFragment<TickerFragmentBinding, TickerViewModel>(
    R.layout.ticker_fragment
) {
    private val firstMarketName = "KRW"
    private val tickerAdapter =
        object :
            ClickAdapter<List<Map<String, String>>, TickerItemBinding>(
                R.layout.ticker_item,
                BR.tickerItem
            ) {}

    override val vm: TickerViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setFirstTickers()
        setOnClick()
    }

    private fun initViewModel() {
        binding.setVariable(BR.vm, vm)

        val errMsgObserver = Observer<Throwable> {
            showToast(it.message)
        }

        vm.errMsg.observe(viewLifecycleOwner, errMsgObserver)
    }

    private fun setAdapter() {
        tickerRecyclerView.adapter = tickerAdapter
    }

    private fun setFirstTickers() =
        vm.showTickers(firstMarketName)

    @SuppressLint("CheckResult")
    private fun setOnClick() {
        tickerAdapter.getOnItemClickObservable().subscribe {
            val detailIntent = Intent(context, DetailActivity::class.java)
            detailIntent.putExtra("currency", vm.tickers.value!!.get(it).get("tickerName"))
            startActivity(detailIntent)
        }
    }
}