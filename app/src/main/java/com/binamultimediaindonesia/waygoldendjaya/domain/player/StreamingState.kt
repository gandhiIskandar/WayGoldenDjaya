package com.binamultimediaindonesia.waygoldendjaya.domain.player

sealed class StreamingState {
    object OnLoading : StreamingState()
    object OnBuffering: StreamingState()
    object OnPlaying: StreamingState()
    object OnReady:StreamingState()
    object OnStop:StreamingState()
    data class  OnError(val message: String) : StreamingState()

}