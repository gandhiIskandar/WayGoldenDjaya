package com.binamultimediaindonesia.waygoldendjaya.download

interface Downloader {

    fun downloadFile(url:String,  token:String):Long

}