package com.example.sdgp_silent_voice.databinding;
import com.example.sdgp_silent_voice.R;
import com.example.sdgp_silent_voice.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RecognitionItemBindingImpl extends RecognitionItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RecognitionItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private RecognitionItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recognitionName.setTag(null);
        this.recognitionProb.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.recognitionItem == variableId) {
            setRecognitionItem((com.example.sdgp_silent_voice.Recognition) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRecognitionItem(@Nullable com.example.sdgp_silent_voice.Recognition RecognitionItem) {
        this.mRecognitionItem = RecognitionItem;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.recognitionItem);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String recognitionItemProbabilityString = null;
        com.example.sdgp_silent_voice.Recognition recognitionItem = mRecognitionItem;
        java.lang.String recognitionItemLabel = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (recognitionItem != null) {
                    // read recognitionItem.probabilityString
                    recognitionItemProbabilityString = recognitionItem.getProbabilityString();
                    // read recognitionItem.label
                    recognitionItemLabel = recognitionItem.getLabel();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.recognitionName, recognitionItemLabel);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.recognitionProb, recognitionItemProbabilityString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): recognitionItem
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}