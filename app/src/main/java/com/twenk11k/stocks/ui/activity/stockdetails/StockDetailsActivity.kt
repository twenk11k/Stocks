package com.twenk11k.stocks.ui.activity.stockdetails

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.ActivityStockDetailsBinding
import com.twenk11k.stocks.model.StockDetailsResponse
import com.twenk11k.stocks.ui.activity.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class StockDetailsActivity : DataBindingActivity() {

    // views
    private lateinit var textSymbol: TextView
    private lateinit var textPrice: TextView
    private lateinit var textDifference: TextView
    private lateinit var textVolume: TextView
    private lateinit var textBuying: TextView
    private lateinit var textSelling: TextView

    private lateinit var textDailyLow: TextView
    private lateinit var textDailyHigh: TextView
    private lateinit var textAmount: TextView
    private lateinit var textCeiling: TextView
    private lateinit var textBase: TextView
    private lateinit var imageChange: ImageView

    private lateinit var lineChart: LineChart

    private var symbol = ""

    private val binding: ActivityStockDetailsBinding by binding(R.layout.activity_stock_details)

    val viewModel: StockDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id", 0)
        this.symbol = intent.getStringExtra("symbol")!!
        val aesKey = intent.getStringExtra("aesKey")
        val aesIV = intent.getStringExtra("aesIV")
        val authorization = intent.getStringExtra("authorization")
        lifecycleScope.launch {
            viewModel.retrieveStockDetailsResponse(id, aesKey!!, aesIV!!, authorization!!).observe(
                this@StockDetailsActivity,
                Observer {
                    updateFields(it)
                })
        }
        setViews()
    }

    private fun updateFields(stockDetailsResponse: StockDetailsResponse) {
        textSymbol.text = stockDetailsResponse.symbol
        textPrice.text = stockDetailsResponse.price.toString()
        textDifference.text = stockDetailsResponse.difference.toString()
        textVolume.text = stockDetailsResponse.volume.toString()
        textBuying.text = stockDetailsResponse.bId.toString()
        textSelling.text = stockDetailsResponse.offer.toString()

        textDailyLow.text = stockDetailsResponse.lowest.toString()
        textDailyHigh.text = stockDetailsResponse.highest.toString()
        textAmount.text = stockDetailsResponse.count.toString()
        textCeiling.text = stockDetailsResponse.maximum.toString()
        textBase.text = stockDetailsResponse.minimum.toString()
        setImageChange(stockDetailsResponse)
        setLineChart(stockDetailsResponse)
    }

    private fun setLineChart(stockDetailsResponse: StockDetailsResponse) {
        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.description.isEnabled = false

        val xAxis = lineChart.xAxis
        // vertical grid lines
        xAxis.enableGridDashedLine(10f, 10f, 0f)

        val yAxis = lineChart.axisLeft

        // disable dual axis (only use LEFT axis)
        lineChart.axisRight.isEnabled = false

        // horizontal grid lines
        yAxis.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis.axisMaximum = stockDetailsResponse.maximum + stockDetailsResponse.maximum / 10
        yAxis.axisMinimum = stockDetailsResponse.minimum - stockDetailsResponse.minimum / 10

        setLineChartData(stockDetailsResponse)
    }

    private fun setLineChartData(stockDetailsResponse: StockDetailsResponse) {
        val values = ArrayList<Entry>()
        for (i in stockDetailsResponse.graphicData.indices) {
            val value = stockDetailsResponse.graphicData[i].value.toFloat()
            values.add(Entry(i.toFloat(), value, ContextCompat.getDrawable(this,R.drawable.line_chart_background)))
        }

        val set: LineDataSet

        if (lineChart.data != null &&
            lineChart.data.dataSetCount > 0
        ) {
            set = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set.values = values
            set.notifyDataSetChanged()
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            set = LineDataSet(values,"")

            set.setDrawIcons(false)

            set.enableDashedLine(10f, 5f, 0f)

            set.color = Color.BLACK
            set.setCircleColor(Color.BLACK)

            set.lineWidth = 1f
            set.circleRadius = 3f

            set.setDrawCircleHole(false)

            set.formSize = 0f
            set.valueTextSize = 10f

            set.enableDashedHighlightLine(10f, 5f, 0f)

            set.setDrawFilled(true)
            set.fillFormatter =
                IFillFormatter { _, _ -> lineChart.axisLeft.axisMinimum }

            val drawable = ContextCompat.getDrawable(this, R.drawable.line_chart_background)
            set.fillDrawable = drawable

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set)

            val data = LineData(dataSets)

            lineChart.data = data
        }
    }

    private fun setImageChange(stockDetailsResponse: StockDetailsResponse) {
        val isDown = stockDetailsResponse.isDown
        val imageChangeDrawable =
            if (isDown) R.drawable.baseline_keyboard_arrow_down_black_18 else R.drawable.baseline_keyboard_arrow_up_black_18

        imageChange.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                imageChangeDrawable
            )
        )

        if (isDown)
            imageChange.setColorFilter(Color.RED)
        else
            imageChange.setColorFilter(Color.GREEN)
    }

    private fun setViews() {
        this.textSymbol = binding.textSymbol
        this.textPrice = binding.textPrice
        this.textDifference = binding.textDifference
        this.textVolume = binding.textVolume
        this.textBuying = binding.textBuying
        this.textSelling = binding.textSelling

        this.textDailyLow = binding.textDailyLow
        this.textDailyHigh = binding.textDailyHigh
        this.textAmount = binding.textAmount
        this.textCeiling = binding.textCeiling
        this.textBase = binding.textBase
        this.imageChange = binding.imageChange

        this.lineChart = binding.lineChart

        supportActionBar?.title = if(symbol.isNotEmpty()) symbol else getString(R.string.stock_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}