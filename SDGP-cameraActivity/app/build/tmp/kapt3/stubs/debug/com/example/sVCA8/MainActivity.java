package com.example.sVCA8;

import java.lang.System;

@permissions.dispatcher.RuntimePermissions()
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0019\u001a\u00020\rH\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0019\u001a\u00020\rH\u0002J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u001dH\u0014J+\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0%2\u0006\u0010&\u001a\u00020\'H\u0016\u00a2\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006+"}, d2 = {"Lcom/example/sVCA8/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "cameraExecutorService", "Ljava/util/concurrent/ExecutorService;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "getInterpreter", "()Lorg/tensorflow/lite/Interpreter;", "interpreter$delegate", "Lkotlin/Lazy;", "labels", "", "", "getLabels", "()Ljava/util/List;", "labels$delegate", "overlaySurfaceView", "Lcom/example/sVCA8/OverlaySurfaceView;", "yuvToRgbConverter", "Lcom/example/sVCA8/YuvToRgbConverter;", "getYuvToRgbConverter", "()Lcom/example/sVCA8/YuvToRgbConverter;", "yuvToRgbConverter$delegate", "loadLabels", "fileName", "loadModel", "Ljava/nio/ByteBuffer;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "setupCamera", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.sVCA8.OverlaySurfaceView overlaySurfaceView;
    private java.util.concurrent.ExecutorService cameraExecutorService;
    private final kotlin.Lazy interpreter$delegate = null;
    private final kotlin.Lazy labels$delegate = null;
    private final kotlin.Lazy yuvToRgbConverter$delegate = null;
    private static final java.lang.String signModel = "ssd_mobilenet_v1.tflite";
    private static final java.lang.String signLabels = "";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.sVCA8.MainActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @permissions.dispatcher.NeedsPermission(value = {"android.permission.CAMERA"})
    public final void setupCamera() {
    }
    
    private final org.tensorflow.lite.Interpreter getInterpreter() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getLabels() {
        return null;
    }
    
    private final com.example.sVCA8.YuvToRgbConverter getYuvToRgbConverter() {
        return null;
    }
    
    private final java.nio.ByteBuffer loadModel(java.lang.String fileName) {
        return null;
    }
    
    private final java.util.List<java.lang.String> loadLabels(java.lang.String fileName) {
        return null;
    }
    
    public MainActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/sVCA8/MainActivity$Companion;", "", "()V", "signLabels", "", "signModel", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}