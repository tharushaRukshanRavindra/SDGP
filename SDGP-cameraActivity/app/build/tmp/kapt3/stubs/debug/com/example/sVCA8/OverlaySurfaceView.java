package com.example.sVCA8;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bJ(\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/sVCA8/OverlaySurfaceView;", "Landroid/view/SurfaceView;", "Landroid/view/SurfaceHolder$Callback;", "surfaceView", "(Landroid/view/SurfaceView;)V", "paint", "Landroid/graphics/Paint;", "pathColorList", "", "", "surfaceHolder", "Landroid/view/SurfaceHolder;", "kotlin.jvm.PlatformType", "draw", "", "detectedObjectList", "Lcom/example/sVCA8/DetectedSign;", "surfaceChanged", "holder", "format", "width", "height", "surfaceCreated", "surfaceDestroyed", "app_debug"})
public final class OverlaySurfaceView extends android.view.SurfaceView implements android.view.SurfaceHolder.Callback {
    private android.view.SurfaceHolder surfaceHolder;
    private final android.graphics.Paint paint = null;
    private final java.util.List<java.lang.Integer> pathColorList = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void surfaceCreated(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder) {
    }
    
    @java.lang.Override()
    public void surfaceChanged(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder, int format, int width, int height) {
    }
    
    @java.lang.Override()
    public void surfaceDestroyed(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder) {
    }
    
    public final void draw(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.sVCA8.DetectedSign> detectedObjectList) {
    }
    
    public OverlaySurfaceView(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceView surfaceView) {
        super(null);
    }
}