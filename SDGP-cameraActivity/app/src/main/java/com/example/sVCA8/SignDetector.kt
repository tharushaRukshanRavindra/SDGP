package com.example.sVCA8
/*
 *main sign detection pipeline
 *
 * implement ImageAnalysis provided by cameraX and create signDetector class that receives
 * and preview of the camera and returns the analysis result.
*/


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.RectF
import android.media.Image
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.Rot90Op

typealias signDetectorCallback = (image: List<DetectedSign>) -> Unit

/**
 * Image analysis use case for camera X sign detection
 * @param yuvToRgbConverter Convert from image buffer YUV_420_888 of camera image to RGB format
 * @param interpreter Library for manipulating tflite models
 * @param labels List of correct labels
 * @param resultViewSize The size of the surfaceView that displays the results
 * @param listener Receive a list of analysis results in the callback
 */
class SignDetector(
    private val yuvToRgbConverter: YuvToRgbConverter,
    private val interpreter: Interpreter,
    private val labels: List<String>,
    private val resultViewSize: Size,
    private val listener: signDetectorCallback
) : ImageAnalysis.Analyzer {

    companion object {
        // Model input and output size
        private const val IMG_SIZE_X = 300
        private const val IMG_SIZE_Y = 300
        private const val MAX_DETECTION_NUM = 10

        // Since the tflite model used this time has been quantized, the normalize related is not 127.5f but as follows
        private const val NORMALIZE_MEAN = 0f
        private const val NORMALIZE_STD = 1f

        // Ditection result score threshold
        private const val SCORE_THRESHOLD = 0.5f
    }

    private var imageRotationDegrees: Int = 0
    private val tfImageProcessor by lazy {
        ImageProcessor.Builder()
            .add(ResizeOp(IMG_SIZE_X, IMG_SIZE_Y, ResizeOp.ResizeMethod.BILINEAR)) // Resize the image to fit the model's input
            .add(Rot90Op(-imageRotationDegrees / 90)) // The flowing Image Proxy is rotated 90 degrees, so its correction
            .add(NormalizeOp(NORMALIZE_MEAN, NORMALIZE_STD)) // Normalization
            .build()
    }

    private val tfImageBuffer = TensorImage(DataType.UINT8)

    // Detection result bounding box [1:10:4]
    // The bounding box is in the form of [top, left, bottom, right]
    private val outputBoundingBoxes: Array<Array<FloatArray>> = arrayOf(
        Array(MAX_DETECTION_NUM) {
            FloatArray(4)
        }
    )

    // Discovery result class label index [1:10]
    private val outputLabels: Array<FloatArray> = arrayOf(
        FloatArray(MAX_DETECTION_NUM)
    )

    // Each score of the detection result [1:10]
    private val outputScores: Array<FloatArray> = arrayOf(
        FloatArray(MAX_DETECTION_NUM)
    )

    // Number of detected objects (10 (constant) because this time it was set at the time of tflite conversion)
    private val outputDetectionNum: FloatArray = FloatArray(1)

    // Put together in a map to receive detection results
    private val outputMap = mapOf(
        0 to outputBoundingBoxes,
        1 to outputLabels,
        2 to outputScores,
        3 to outputDetectionNum
    )

    // Infer the preview image flowing from cameraX by putting it in the object detection model.
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(image: ImageProxy) {
        if (image.image == null) return
        imageRotationDegrees = image.imageInfo.rotationDegrees
        val detectedObjectList = detect(image.image!!)
        listener(detectedObjectList)
        image.close()
    }

    // Convert the image to YUV-> RGB bitmap-> tensorflowImage-> tensorflowBuffer, infer and output the result as a list
    private fun detect(targetImage: Image): List<DetectedSign> {
        val targetBitmap = Bitmap.createBitmap(targetImage.width, targetImage.height, Bitmap.Config.ARGB_8888)
        yuvToRgbConverter.yuvToRgb(targetImage, targetBitmap) // Convert to rgb
        tfImageBuffer.load(targetBitmap)
        val tensorImage = tfImageProcessor.process(tfImageBuffer)

        //Performing inference with the tflite model
        interpreter.runForMultipleInputsOutputs(arrayOf(tensorImage.buffer), outputMap)

        // Format the inference result and return it as a list
        val detectedObjectList = arrayListOf<DetectedSign>()
        loop@ for (i in 0 until outputDetectionNum[0].toInt()) {
            val score = outputScores[0][i]
            val label = labels[outputLabels[0][i].toInt()]
            val boundingBox = RectF(
                outputBoundingBoxes[0][i][1] * resultViewSize.width,
                outputBoundingBoxes[0][i][0] * resultViewSize.height,
                outputBoundingBoxes[0][i][3] * resultViewSize.width,
                outputBoundingBoxes[0][i][2] * resultViewSize.height
            )

            // Add only those that are larger than the threshold
            if (score >= SCORE_THRESHOLD) {
                detectedObjectList.add(
                    DetectedSign(
                        score = score,
                        label = label,
                        boundingBox = boundingBox
                    )
                )
            } else {
                // the loop ends when the detection result contains the
                    // highest marked layer below the threshold. and break loop
                break@loop
            }
        }
        return detectedObjectList.take(4)
    }
}
