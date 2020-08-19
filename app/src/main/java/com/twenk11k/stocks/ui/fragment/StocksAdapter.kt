package com.twenk11k.stocks.ui.fragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.stocks.App
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.AdapterItemStocksBinding
import com.twenk11k.stocks.model.Stock

class StocksAdapter(private val context: Context, private var listStock: ArrayList<Stock>) :
    RecyclerView.Adapter<StocksAdapter.ViewHolder>() {

    private var listener: StocksAdapterClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StocksAdapter.ViewHolder {
        val binding: AdapterItemStocksBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_item_stocks,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listStock.size
    }

    override fun onBindViewHolder(holder: StocksAdapter.ViewHolder, position: Int) {
        holder.bind(listStock[holder.adapterPosition])
    }

    fun setListener(listener: StocksAdapterClickListener) {
        this.listener = listener
    }

    fun addListStock(listStock: ArrayList<Stock>) {
        this.listStock = listStock
        notifyDataSetChanged()
    }

    fun filterList(listFiltered: ArrayList<Stock>) {
        this.listStock = listFiltered
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(val binding: AdapterItemStocksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: Stock) {
            if (adapterPosition % 2 == 1)
                binding.parentLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        App.getContext(),
                        R.color.gray_2
                    )
                )
            else
                binding.parentLayout.setBackgroundColor(Color.WHITE)

            binding.textSymbol.text = stock.symbol
            binding.textPrice.text = stock.price.toString()
            binding.textDifference.text = stock.difference.toString()
            binding.textPrice.text = stock.price.toString()
            binding.textVolume.text = stock.volume.toString().subSequence(0, 3)
            binding.textBuying.text = stock.bId.toString()
            binding.textSelling.text = stock.offer.toString()

            val isDown = stock.isDown
            val imageChangeDrawable =
                if (isDown) R.drawable.baseline_keyboard_arrow_down_black_18 else R.drawable.baseline_keyboard_arrow_up_black_18

            binding.imageChange.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    imageChangeDrawable
                )
            )

            if (isDown)
                binding.imageChange.setColorFilter(Color.RED)
            else
                binding.imageChange.setColorFilter(Color.GREEN)

            binding.root.setOnClickListener {
                if (listener != null && adapterPosition != RecyclerView.NO_POSITION) {
                    listener?.onAdapterClick(stock.id, stock.symbol)
                }
            }
        }

    }

}
