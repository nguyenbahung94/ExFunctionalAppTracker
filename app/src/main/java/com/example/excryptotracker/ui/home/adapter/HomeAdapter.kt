package com.example.excryptotracker.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.excryptotracker.R
import com.example.excryptotracker.extensions.getDrawableCompat
import com.example.excryptotracker.extensions.safeToLong
import com.example.excryptotracker.extensions.unixToDate
import com.example.excryptotracker.model.CurrencyModel
import kotlinx.android.synthetic.main.coin_item.view.*

class HomeAdapter(val items: MutableList<CurrencyModel>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    companion object {
        const val MINUS = "-"
    }

    fun updateListData(newItems: List<CurrencyModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    var listener: HolderListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return HomeViewHolder(rootView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    @SuppressLint("SetTextI18n")
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CurrencyModel, listener: HolderListener?) {
            with(itemView) {
                tvCode.text = "(${item.symbol})"
                tvName.text = item.name
                tvPrice.text = "$${item.priceUsd}"
                item.dayPercentChange?.let {
                    tvStatsDaily.text = "$it %"
                    val arrowStats = if (it.contains(MINUS)) R.drawable.arrow_down else R.drawable.arrow_up
                    ivIndicatorDaily.setImageDrawable(itemView.context.getDrawableCompat(arrowStats))
                }
                item.weekPercentChange?.let {
                    tvStatsWeekly.text = "$it %"
                    val arrowStats = if (it.contains(MINUS)) R.drawable.arrow_down else R.drawable.arrow_up
                    ivIndicatorWeekly.setImageDrawable(itemView.context.getDrawableCompat(arrowStats))
                }
                item.hourPercentChange?.let {
                    tvStatsHourly.text = "$it %"
                    val arrowStats = if (it.contains(MINUS)) R.drawable.arrow_down else R.drawable.arrow_up
                    ivIndicatorHourly.setImageDrawable(itemView.context.getDrawableCompat(arrowStats))
                }
                item.lastUpdated?.let {
                    val unixTimestamp = it.safeToLong()
                    tvLastUpdate.text = unixTimestamp.unixToDate()
                }
                setOnClickListener { listener?.onClickHolder(adapterPosition) }
            }
        }
    }


    interface HolderListener {
        fun onClickHolder(position: Int)
    }
}