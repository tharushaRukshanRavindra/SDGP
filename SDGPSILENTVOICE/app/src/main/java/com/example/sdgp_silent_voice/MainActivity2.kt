package com.example.sdgp_silent_voice

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import com.example.sdgp_silent_voice.ml.RdModel
import com.example.sdgp_silent_voice.ml.SignModel9
import com.example.sdgp_silent_voice.ml.SignModelTFLITE
import com.example.sdgp_silent_voice.ml.SignRecog
import org.tensorflow.lite.support.image.TensorImage
import java.util.concurrent.Executors
import kotlin.reflect.KClass


// CONSTANTS
// maximum number of results display
private const val NumOfResultDisplaying = 1

// name for logging
private const val tag = "SignRecognition"

// return code after permission
private const val requestCodePermission = 999

// get permission
private val requiredPermission = arrayOf(android.Manifest.permission.CAMERA)

// listener for the result of the ImageAnalyzer
typealias RecognitionListener = (recognition: List<Recognition>) -> Unit


// main entry point into TensorFlow Lite classifier
class MainActivity2 : AppCompatActivity() {

    // cameraX variables

    // preview use case, fast, responsive view of the camera
    private lateinit var preview: Preview

    // analysis use case, for running ML code
    private lateinit var imageAnalyzer: ImageAnalysis
    private lateinit var camera: Camera
    private val cameraExecutor = Executors.newSingleThreadExecutor()

    // views attachment
    // display the result of analysis
    private val resultRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recognitionResults)
    }

    // display the preview image from camera
    private val viewFinder by lazy {
        findViewById<PreviewView>(R.id.viewFinder)
    }

    // contains the recognition result. since it is a viewModel, it will survive screen retations
    private val recogViewModel: RecognitionListViewModel by viewModels()


    //----------------------------------------------------------------------------------------------------------
    // for viewModels()
    @MainThread
    inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
    }

    class ViewModelLazy<VM : ViewModel> (
        private val viewModelClass: KClass<VM>,
        private val storeProducer: () -> ViewModelStore,
        private val factoryProducer: () -> ViewModelProvider.Factory
    ) : Lazy<VM> {
        private var cached: VM? = null

        override val value: VM
            get() {
                val viewModel = cached
                return if (viewModel == null) {
                    val factory = factoryProducer()
                    val store = storeProducer()
                    ViewModelProvider(store, factory).get(viewModelClass.java).also {
                        cached = it
                    }
                } else {
                    viewModel
                }
            }

        override fun isInitialized() = cached != null
    }
    //----------------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, requiredPermission, requestCodePermission
            )
        }

        // initializing the resultRecycleView and its linked viewAdapter
        val viewAdapter = RecognitionAdapter(this)
        resultRecyclerView.adapter = viewAdapter

        // disable recycler view animation to educe flickering,
        // or items can move, fade in and out as the list change
        recogViewModel.recognitionList.observe(this
        ) {
            viewAdapter.submitList(it)
        }
    }
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener( {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            preview = Preview.Builder()
                .build()

            imageAnalyzer = ImageAnalysis.Builder()
                // This sets the ideal size for the image to be analyse, CameraX will choose the
                // the most suitable resolution which may not be exactly the same or hold the same
                // aspect ratio
                .setTargetResolution(Size(40, 40))
                // How the Image Analyser should pipe in input, 1. every frame but drop no frame, or
                // 2. go to the latest frame and may drop some frame. The default is 2.
                // STRATEGY_KEEP_ONLY_LATEST. The following line is optional, kept here for clarity
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { analysisUseCase: ImageAnalysis ->
                    analysisUseCase.setAnalyzer(cameraExecutor, ImageAnalyzer(this) { items ->
                        // updating the list of recognised objects
                        recogViewModel.updateData(items)
                    })
                }

            // Select camera, back is the default. If it is not available, choose front camera
            val cameraSelector =
                if (cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA))
                    CameraSelector.DEFAULT_BACK_CAMERA else CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera - try to bind everything at once and CameraX will find
                // the best combination.
                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer
                )

                // Attach the preview to preview view, aka View Finder
                preview.setSurfaceProvider(viewFinder.surfaceProvider)
            } catch (exc: Exception) {
                Log.e(tag, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted(): Boolean = requiredPermission.all{
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodePermission) {
            if (allPermissionsGranted()) {
               startCamera()
            } else {
                // exit the app if permission not granted
                Toast.makeText(
                    this,
                    "Permission not granted",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}

private class ImageAnalyzer(ctx: Context, private val listener: RecognitionListener) :
    ImageAnalysis.Analyzer {

        // Add class variable TeasorFlow Lite model
        /* Initializing the signModel by lazy so that it runs in the
           same thread when the process method is called
         */
        private val signModel = SignRecog.newInstance(ctx)

    override fun analyze(imageProxy: ImageProxy) {

        val items = mutableListOf<Recognition>()

        // convert image into bitmap then to tensorImage
        val tfImage = TensorImage.fromBitmap(toBitmap(imageProxy))

        // process the image using the trained model,
        // sort and pick out the top result
        val outputs = signModel.process(tfImage).probabilityAsCategoryList.apply {
            sortByDescending { it.score }
        }.take(NumOfResultDisplaying)

//         converting the top probability items into a list of recognitions
        for (output in outputs) {
            items.add(Recognition(output.label, output.score))
        }

        // return the result
        listener(items.toList())

        // close the image
        // this tell cameraX to feed the next image to the analyzer
        imageProxy.close()


    }



    private val yuvToRgbConverter = YuvToRgbConverter(ctx)
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var rotationMatrix: Matrix

    @SuppressLint("UnsafeOptInUsageError")
    private fun toBitmap(imageProxy: ImageProxy): Bitmap? {

        val image = imageProxy.image ?: return null

        // Initialise Buffer
        if (!::bitmapBuffer.isInitialized) {
            // The image rotation and RGB image buffer are initialized only once
            Log.d(tag, "Initalise toBitmap()")
            rotationMatrix = Matrix()
            rotationMatrix.postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            bitmapBuffer = Bitmap.createBitmap(
                imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
            )
        }

        // Pass image to an image analyser
        yuvToRgbConverter.yuvToRgb(image, bitmapBuffer)

        // Create the Bitmap in the correct orientation
        return Bitmap.createBitmap(
            bitmapBuffer,
            0,
            0,
            bitmapBuffer.width,
            bitmapBuffer.height,
            rotationMatrix,
            false
        )
    }


}


