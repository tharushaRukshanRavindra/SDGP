package com.example.sVCA8

import android.Manifest
import android.content.res.AssetFileDescriptor
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.tensorflow.lite.Interpreter
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@RuntimePermissions
class MainActivity : AppCompatActivity() {


    private lateinit var overlaySurfaceView: OverlaySurfaceView
    private lateinit var cameraExecutorService: ExecutorService



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overlaySurfaceView = OverlaySurfaceView(viewResult)

        cameraExecutorService = Executors.newSingleThreadExecutor()
        // Call the setUpCamera () method with permissionDispatcher
        setupCameraWithPermissionCheck()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutorService.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    // implementing permission dispatcher for camera permission requests
    // A function for the permission request is auto generated
    @NeedsPermission(Manifest.permission.CAMERA)
    fun setupCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview use case
            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(viewCamera.surfaceProvider) }

            // Using rear camera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // Use case for image analysis (this time object detection)
            val imageAnalyzer = ImageAnalysis.Builder()
                .setTargetRotation(viewCamera.display.rotation)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // showing only the latest camera preview
                .build()
                .also {
                    it.setAnalyzer(
                        cameraExecutorService,
                        SignDetector(
                            yuvToRgbConverter,
                            interpreter,
                            labels,
                            Size(viewResult.width, viewResult.height)
                        ) { detectedObjectList ->
                            // Display of analysis results
                            overlaySurfaceView.draw(detectedObjectList)
                        }
                    )
                }

            try {
                cameraProvider.unbindAll()

                // Bind each use case to cameraX
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)

            } catch (exc: Exception) {
                Log.e("ERROR: Camera", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    // Load tflite model from assets

    // put model and label text in to the assets
    companion object {
        private const val signModel = "ssd_mobilenet_v1.tflite" // model name goes here
        private const val signLabels = "" // labels text file name goes here
    }

    // loading the model
    // lazy evaluation delays the evaluation of an expression until its value is needed
    private val interpreter: Interpreter by lazy {
        Interpreter(loadModel())
    }

    // model label list
    private val labels: List<String> by lazy {
        loadLabels()
    }

    // converting Yuv image to RGB image
    private val yuvToRgbConverter: YuvToRgbConverter by lazy {
        YuvToRgbConverter(this)
    }

    private fun loadModel(fileName: String = signModel): ByteBuffer {
        lateinit var modelBuffer: ByteBuffer
        var file: AssetFileDescriptor? = null
        try {
            file = assets.openFd(fileName)
            val inputStream = FileInputStream(file.fileDescriptor)
            val fileChannel = inputStream.channel
            modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, file.startOffset, file.declaredLength)
        } catch (e: Exception) {
            Toast.makeText(this, "Model file read error", Toast.LENGTH_SHORT).show()
            finish()
        } finally {
            file?.close()
        }
        return modelBuffer
    }

    // Get label data of the model from assets
    private fun loadLabels(fileName: String = signLabels): List<String> {
        var labels = listOf<String>()
        var inputStream: InputStream? = null
        try {
            inputStream = assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            labels = reader.readLines()
        } catch (e: Exception) {
            Toast.makeText(this, "txt file read error", Toast.LENGTH_SHORT).show()
            finish()
        } finally {
            inputStream?.close()
        }
        return labels
    }
}
