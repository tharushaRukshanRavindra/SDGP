package com.example.sdgp_silent_voice;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B:\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012+\u0010\u0004\u001a\'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\f\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R3\u0010\u0004\u001a\'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/sdgp_silent_voice/ImageAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "ctx", "Landroid/content/Context;", "listener", "Lkotlin/Function1;", "", "Lcom/example/sdgp_silent_voice/Recognition;", "Lkotlin/ParameterName;", "name", "recognition", "", "Lcom/example/sdgp_silent_voice/RecognitionListener;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "bitmapBuffer", "Landroid/graphics/Bitmap;", "rotationMatrix", "Landroid/graphics/Matrix;", "signModel", "Lcom/example/sdgp_silent_voice/ml/SignModelTFLITE;", "yuvToRgbConverter", "Lcom/example/sdgp_silent_voice/YuvToRgbConverter;", "analyze", "imageProxy", "Landroidx/camera/core/ImageProxy;", "toBitmap", "app_debug"})
final class ImageAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
    private final kotlin.jvm.functions.Function1<java.util.List<com.example.sdgp_silent_voice.Recognition>, kotlin.Unit> listener = null;
    private final com.example.sdgp_silent_voice.ml.SignModelTFLITE signModel = null;
    private final com.example.sdgp_silent_voice.YuvToRgbConverter yuvToRgbConverter = null;
    private android.graphics.Bitmap bitmapBuffer;
    private android.graphics.Matrix rotationMatrix;
    
    public ImageAnalyzer(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.sdgp_silent_voice.Recognition>, kotlin.Unit> listener) {
        super();
    }
    
    @java.lang.Override()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
    
    @android.annotation.SuppressLint(value = {"UnsafeOptInUsageError"})
    private final android.graphics.Bitmap toBitmap(androidx.camera.core.ImageProxy imageProxy) {
        return null;
    }
}