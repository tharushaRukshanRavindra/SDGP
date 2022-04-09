package com.example.sVCA8

import android.graphics.*
import android.view.SurfaceHolder
import android.view.SurfaceView


 // Transparent surfaceView that displays the detection result
class OverlaySurfaceView(surfaceView: SurfaceView) :
    SurfaceView(surfaceView.context), SurfaceHolder.Callback {

    init {
        surfaceView.holder.addCallback(this)
        surfaceView.setZOrderOnTop(true)
    }

    private var surfaceHolder = surfaceView.holder
    private val paint = Paint()
    private val pathColorList = listOf(Color.RED, Color.GREEN, Color.CYAN, Color.BLUE)

    override fun surfaceCreated(holder: SurfaceHolder) {
        // Make surfaceView transparent
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    // Display object detection result on surfaceView
    fun draw(detectedObjectList: List<DetectedSign>) {
        // Canvas acquisition via surfaceHolder (Since it may be drawn even at onStop and exception may occur, make it nullable and handle it below)
        val canvas: Canvas? = surfaceHolder.lockCanvas()
        // Clear what was drawn before
        canvas?.drawColor(0, PorterDuff.Mode.CLEAR)

        detectedObjectList.mapIndexed { i, detectionObject ->
            // Displaying the boundingBox
            paint.apply {
                color = pathColorList[i]
                style = Paint.Style.STROKE
                strokeWidth = 7f
                isAntiAlias = false
            }
            canvas?.drawRect(detectionObject.boundingBox, paint)

            // display Label and score
            paint.apply {
                style = Paint.Style.FILL
                isAntiAlias = true
                textSize = 77f
            }
            canvas?.drawText(
                detectionObject.label + " " + "%,.2f".format(detectionObject.score * 100) + "%",
                detectionObject.boundingBox.left,
                detectionObject.boundingBox.top - 5f,
                paint
            )
        }

        surfaceHolder.unlockCanvasAndPost(canvas ?: return)
    }
}
