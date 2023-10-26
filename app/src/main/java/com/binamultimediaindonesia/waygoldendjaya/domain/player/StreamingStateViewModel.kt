package com.binamultimediaindonesia.waygoldendjaya.domain.player


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class StreamingStateViewModel  : ViewModel() {
    private val playbackStateFlow = mutableStateOf<StreamingState>(StreamingState.OnStop)

    val playbackState: State<StreamingState> = playbackStateFlow

    private val _urlFlow = MutableStateFlow<String>("")

    val urlFlow: StateFlow<String> = _urlFlow



    fun updateUrl(url:String){
        viewModelScope.launch {
            _urlFlow.emit(url)
        }

    }


    fun playStreamingState(){

        playbackStateFlow.value = StreamingState.OnPlaying

    }

    fun loadingStreamingState(){
        playbackStateFlow.value = StreamingState.OnLoading
    }

    fun stopStreamingState(){
        playbackStateFlow.value = StreamingState.OnStop
    }

    fun readyStreamingState(){
        playbackStateFlow.value = StreamingState.OnReady
    }

    fun bufferingStreamingState(){
        playbackStateFlow.value = StreamingState.OnBuffering
    }

    fun errorStreamingState(message:String){
        playbackStateFlow.value = StreamingState.OnError(message)
    }






}