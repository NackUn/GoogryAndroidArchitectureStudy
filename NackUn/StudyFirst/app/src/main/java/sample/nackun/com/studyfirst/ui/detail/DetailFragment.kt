package sample.nackun.com.studyfirst.ui.detail

import android.os.Bundle
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.base.BaseRecyclerView
import sample.nackun.com.studyfirst.databinding.DetailFragmentBinding
import sample.nackun.com.studyfirst.databinding.TickerItemBinding

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>(
    R.layout.detail_fragment
) {
    private val detailAdapter =
        object :
            BaseRecyclerView.BaseAdapter<List<Map<String, String>>, TickerItemBinding>(
                R.layout.ticker_item,
                BR.tickerItem
            ) {}

    override val vm: DetailViewModel by viewModel {
        parametersOf(activity!!.intent.getStringExtra("currency"))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setTickers()
    }

    private fun initViewModel() {
        binding.detailViewModel = vm
    }

    private fun setAdapter() {
        detail_recyclerView.adapter = detailAdapter
    }

    private fun setTickers() =
        vm.showTickers()
}