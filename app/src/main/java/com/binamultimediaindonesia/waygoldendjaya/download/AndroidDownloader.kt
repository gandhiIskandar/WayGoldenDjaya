package com.binamultimediaindonesia.waygoldendjaya.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
):Downloader {

    private val downloadManager=context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String,  token:String): Long {

       // val fileName = "sertifikat_${name.replace(" ","_")}.pdf"

        val request = DownloadManager.Request("https://admin.waygoldendjaya.com/pdfTest/example.pdf".toUri())
            .setMimeType("application/pdf")
            .setTitle("Download Sertifikat WGD")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
          //  .addRequestHeader("Authorization", "Bearer $token")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "serifikat.pdf")

        return downloadManager.enqueue(request)

    }
}