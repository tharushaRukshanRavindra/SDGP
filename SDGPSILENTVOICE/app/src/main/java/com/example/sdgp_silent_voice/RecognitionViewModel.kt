package com.example.sdgp_silent_voice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ----------------------------------------------------------------------------
// from RecognitionViewModel
data class Recognition(val label:String, val confidence:Float) {

    // For easy logging
    override fun toString():String{
        return "$label / $probabilityString"
    }

    // Output probability as a string to enable easy data binding
    val probabilityString = String.format("%.1f%%", confidence * 100.0f)

}
//------------------------------------------------------------------------------

class RecognitionListViewModel: ViewModel() {

    // This is a LiveData field. Choosing this structure because the whole list tend to be updated
    // at once in ML and not individual elements. Updating this once for the entire list makes
    // sense.
    private val _recognitionList = MutableLiveData<List<Recognition>>()
    val recognitionList: LiveData<List<Recognition>> = _recognitionList

    fun updateData(recognitions: List<Recognition>){
        _recognitionList.postValue(recognitions)
    }

}
//--------------------------------------------------------------------------------------
