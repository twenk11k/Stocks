package com.twenk11k.stocks.extension

import android.view.View

/** makes a view visible. */
fun View.visible() {
    visibility = View.VISIBLE
}

/** makes a view gone. */
fun View.gone() {
    visibility = View.GONE
}