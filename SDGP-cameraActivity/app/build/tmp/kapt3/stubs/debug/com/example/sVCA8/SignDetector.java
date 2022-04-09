package com.example.sVCA8;

import java.lang.System;

/**
 * Image analysis use case for camera X sign detection
 * @param yuvToRgbConverter Convert from image buffer YUV_420_888 of camera image to RGB format
 * @param interpreter Library for manipulating tflite models
 * @param labels List of correct labels
 * @param resultViewSize The size of the surfaceView that displays the results
 * @param listener Receive a list of analysis results in the callback
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 /2\u00020\u0001:\u0001/BX\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012+\u0010\u000b\u001a\'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0007\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fj\u0002`\u0012\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010*\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020+H\u0017J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010-\u001a\u00020.H\u0002R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R3\u0010\u000b\u001a\'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0007\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fj\u0002`\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0017X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001cR\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001cR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010#\u001a\n %*\u0004\u0018\u00010$0$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010\'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/example/sVCA8/SignDetector;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "yuvToRgbConverter", "Lcom/example/sVCA8/YuvToRgbConverter;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "labels", "", "", "resultViewSize", "Landroid/util/Size;", "listener", "Lkotlin/Function1;", "Lcom/example/sVCA8/DetectedSign;", "Lkotlin/ParameterName;", "name", "image", "", "Lcom/example/sVCA8/signDetectorCallback;", "(Lcom/example/sVCA8/YuvToRgbConverter;Lorg/tensorflow/lite/Interpreter;Ljava/util/List;Landroid/util/Size;Lkotlin/jvm/functions/Function1;)V", "imageRotationDegrees", "", "outputBoundingBoxes", "", "", "[[[F", "outputDetectionNum", "outputLabels", "[[F", "outputMap", "", "", "outputScores", "tfImageBuffer", "Lorg/tensorflow/lite/support/image/TensorImage;", "tfImageProcessor", "Lorg/tensorflow/lite/support/image/ImageProcessor;", "kotlin.jvm.PlatformType", "getTfImageProcessor", "()Lorg/tensorflow/lite/support/image/ImageProcessor;", "tfImageProcessor$delegate", "Lkotlin/Lazy;", "analyze", "Landroidx/camera/core/ImageProxy;", "detect", "targetImage", "Landroid/media/Image;", "Companion", "app_debug"})
public final class SignDetector implements androidx.camera.core.ImageAnalysis.Analyzer {
    private int imageRotationDegrees = 0;
    private final kotlin.Lazy tfImageProcessor$delegate = null;
    private final org.tensorflow.lite.support.image.TensorImage tfImageBuffer = null;
    private final float[][][] outputBoundingBoxes = null;
    private final float[][] outputLabels = null;
    private final float[][] outputScores = null;
    private final float[] outputDetectionNum = null;
    private final java.util.Map<java.lang.Integer, java.lang.Object> outputMap = null;
    private final com.example.sVCA8.YuvToRgbConverter yuvToRgbConverter = null;
    private final org.tensorflow.lite.Interpreter interpreter = null;
    private final java.util.List<java.lang.String> labels = null;
    private final android.util.Size resultViewSize = null;
    private final kotlin.jvm.functions.Function1<java.util.List<com.example.sVCA8.DetectedSign>, kotlin.Unit> listener = null;
    private static final int IMG_SIZE_X = 300;
    private static final int IMG_SIZE_Y = 300;
    private static final int MAX_DETECTION_NUM = 10;
    private static final float NORMALIZE_MEAN = 0.0F;
    private static final float NORMALIZE_STD = 1.0F;
    private static final float SCORE_THRESHOLD = 0.5F;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.sVCA8.SignDetector.Companion Companion = null;
    
    private final org.tensorflow.lite.support.image.ImageProcessor getTfImageProcessor() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"UnsafeExperimentalUsageError"})
    @java.lang.Override()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy image) {
    }
    
    private final java.util.List<com.example.sVCA8.DetectedSign> detect(android.media.Image targetImage) {
        return null;
    }
    
    public SignDetector(@org.jetbrains.annotations.NotNull()
    com.example.sVCA8.YuvToRgbConverter yuvToRgbConverter, @org.jetbrains.annotations.NotNull()
    org.tensorflow.lite.Interpreter interpreter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> labels, @org.jetbrains.annotations.NotNull()
    android.util.Size resultViewSize, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.sVCA8.DetectedSign>, kotlin.Unit> listener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/sVCA8/SignDetector$Companion;", "", "()V", "IMG_SIZE_X", "", "IMG_SIZE_Y", "MAX_DETECTION_NUM", "NORMALIZE_MEAN", "", "NORMALIZE_STD", "SCORE_THRESHOLD", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}