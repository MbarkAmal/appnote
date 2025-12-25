package com.example.appnote.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
