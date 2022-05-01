package com.example.sdgp_silent_voice;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00014B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J+\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&2\u0006\u0010(\u001a\u00020)H\u0016\u00a2\u0006\u0002\u0010*J\b\u0010+\u001a\u00020\u001fH\u0002J4\u0010,\u001a\b\u0012\u0004\u0012\u0002H.0-\"\n\b\u0000\u0010.\u0018\u0001*\u00020/*\u0002002\u0010\b\n\u00101\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102H\u0087\b\u00f8\u0001\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00130\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R#\u0010\u0017\u001a\n \u0007*\u0004\u0018\u00010\u00180\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u00065"}, d2 = {"Lcom/example/sdgp_silent_voice/MainActivity2;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "camera", "Landroidx/camera/core/Camera;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "imageAnalyzer", "Landroidx/camera/core/ImageAnalysis;", "preview", "Landroidx/camera/core/Preview;", "recogViewModel", "Lcom/example/sdgp_silent_voice/RecognitionListViewModel;", "getRecogViewModel", "()Lcom/example/sdgp_silent_voice/RecognitionListViewModel;", "recogViewModel$delegate", "Lkotlin/Lazy;", "resultRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getResultRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "resultRecyclerView$delegate", "viewFinder", "Landroidx/camera/view/PreviewView;", "getViewFinder", "()Landroidx/camera/view/PreviewView;", "viewFinder$delegate", "allPermissionsGranted", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "startCamera", "viewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "ViewModelLazy", "app_debug"})
public final class MainActivity2 extends androidx.appcompat.app.AppCompatActivity {
    private androidx.camera.core.Preview preview;
    private androidx.camera.core.ImageAnalysis imageAnalyzer;
    private androidx.camera.core.Camera camera;
    private final java.util.concurrent.ExecutorService cameraExecutor = null;
    private final kotlin.Lazy resultRecyclerView$delegate = null;
    private final kotlin.Lazy viewFinder$delegate = null;
    private final kotlin.Lazy recogViewModel$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity2() {
        super();
    }
    
    private final androidx.recyclerview.widget.RecyclerView getResultRecyclerView() {
        return null;
    }
    
    private final androidx.camera.view.PreviewView getViewFinder() {
        return null;
    }
    
    private final com.example.sdgp_silent_voice.RecognitionListViewModel getRecogViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void startCamera() {
    }
    
    private final boolean allPermissionsGranted() {
        return false;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B/\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0012\u0010\f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00028\u00008VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/sdgp_silent_voice/MainActivity2$ViewModelLazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Lkotlin/Lazy;", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelStore;", "factoryProducer", "Landroidx/lifecycle/ViewModelProvider$Factory;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "cached", "Landroidx/lifecycle/ViewModel;", "value", "getValue", "()Landroidx/lifecycle/ViewModel;", "isInitialized", "", "app_debug"})
    public static final class ViewModelLazy<VM extends androidx.lifecycle.ViewModel> implements kotlin.Lazy<VM> {
        private final kotlin.reflect.KClass<VM> viewModelClass = null;
        private final kotlin.jvm.functions.Function0<androidx.lifecycle.ViewModelStore> storeProducer = null;
        private final kotlin.jvm.functions.Function0<androidx.lifecycle.ViewModelProvider.Factory> factoryProducer = null;
        private VM cached;
        
        public ViewModelLazy(@org.jetbrains.annotations.NotNull()
        kotlin.reflect.KClass<VM> viewModelClass, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<? extends androidx.lifecycle.ViewModelStore> storeProducer, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<? extends androidx.lifecycle.ViewModelProvider.Factory> factoryProducer) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public VM getValue() {
            return null;
        }
        
        @java.lang.Override()
        public boolean isInitialized() {
            return false;
        }
    }
}