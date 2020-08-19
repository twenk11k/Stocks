package com.twenk11k.stocks.ui.activity.stockdetails

import android.graphics.Color
import android.graphics.DashPathEffect
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

        val set1: LineDataSet

        if (lineChart.data != null &&
            lineChart.data.dataSetCount > 0
        ) {
            set1 = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values,"")

            set1.setDrawIcons(false)

            set1.enableDashedLine(10f, 5f, 0f)

            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            set1.lineWidth = 1f
            set1.circleRadius = 3f

            set1.setDrawCircleHole(false)

            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 0f

            set1.valueTextSize = 9f

            set1.enableDashedHighlightLine(10f, 5f, 0f)

            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { _, _ -> lineChart.axisLeft.axisMinimum }

            val drawable = ContextCompat.getDrawable(this, R.drawable.line_chart_background)
            set1.fillDrawable = drawable

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)

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