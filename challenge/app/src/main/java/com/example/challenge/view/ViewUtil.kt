package com.example.challenge.view

import android.app.Activity
import android.graphics.Point


fun getWindowDimensions(activity: Activity): Point {
    val display = activity.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}