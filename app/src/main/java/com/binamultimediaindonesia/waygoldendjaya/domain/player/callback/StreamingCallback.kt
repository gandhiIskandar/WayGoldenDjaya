package com.binamultimediaindonesia.waygoldendjaya.domain.player.callback

interface  StreamingCallback {

    fun onStartStreaming()

    fun onStopStreaming()

    fun prepareStreaming(url:String)

}