package com.muvi.core

import android.view.View
import android.widget.TextView

fun TextView.textOrGone(newText: CharSequence?) {
    text = newText
    visibility = if (newText == null) View.GONE else View.VISIBLE
}
