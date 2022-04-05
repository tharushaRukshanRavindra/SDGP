package com.example.sVCA8

import android.graphics.RectF

// Detection resilts goes here
data class DetectedSign(
    val score: Float,
    val label: String,
    val boundingBox: RectF
)