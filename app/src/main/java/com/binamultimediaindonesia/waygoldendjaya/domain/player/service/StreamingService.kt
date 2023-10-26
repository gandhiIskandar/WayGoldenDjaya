package com.binamultimediaindonesia.waygoldendjaya.domain.player.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.binamultimediaindonesia.waygoldendjaya.domain.player.callback.StreamingCallback

class StreamingService : Service() {

    private  var player: ExoPlayer? =null

    private lateinit var callback:StreamingCallback

    private val playerListener =  object : Player.Listener {


        override fun onIsPlayingChanged(isPlaying: Boolean) {
            if(!isPlaying){
             //   callback.onStopStreaming()
            }
        }

        override fun onPlayerError(error: PlaybackException) {
//            callback.onStopStreaming()
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): StreamingService {
            return this@StreamingService
        }
    }

    fun setCallback(callback: StreamingCallback){
        this.callback = callback
    }

    @UnstableApi
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
     //   val mediaUrl = intent?.getStringExtra("stream_url")

        Log.d("streamingservice", "start")

//        if (mediaUrl != null) {
            playMedia("http://23.105.253.236:8100/chillout")
      //  }

        return START_STICKY

    }

    @UnstableApi
    private fun playMedia(streamUrl: String) {
        releasePlayer()

        player = ExoPlayer.Builder(this)
            .build()

      //  player!!.addListener(playerListener)

        // Create a media item
        val mediaItem = MediaItem.fromUri(streamUrl)

        // Create a media source and pass the media item
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultHttpDataSource.Factory()
        )
            .createMediaSource(mediaItem)

        // Prepare the player with the media source
        player!!.setMediaSource(mediaSource)
        player!!.prepare()

        // Start playback
        player!!.playWhenReady = true

       // player!!.play()

        Log.d("streamingservice", player!!.isPlaying.toString())

      //  callback.onStartStreaming()


    }

    private fun releasePlayer() {
        player?.release()
        player = null

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}