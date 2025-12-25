package com.example.appnote.ui.theme

import androidx.compose.ui.graphics.Color

import kotlin.random.Random

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Teal200 = Color(0xFF03DAC5)

val noteColors = listOf(
    Color(0xFFFFF59D), // yellow
    Color(0xFFA5D6A7), // green
    Color(0xFF90CAF9), // blue
    Color(0xFFF48FB1), // pink
    Color(0xFFFFCC80), // orange
    Color(0xFFCE93D8)  // purple
)

fun getNoteColor(id: Int): Color {
    return noteColors[id % noteColors.size]
}