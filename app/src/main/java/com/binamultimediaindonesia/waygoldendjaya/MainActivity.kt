package com.binamultimediaindonesia.waygoldendjaya

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.BOTTOM_MENU_LIST
import com.binamultimediaindonesia.waygoldendjaya.domain.player.StreamingState
import com.binamultimediaindonesia.waygoldendjaya.domain.player.StreamingStateViewModel
import com.binamultimediaindonesia.waygoldendjaya.domain.player.callback.StreamingCallback
import com.binamultimediaindonesia.waygoldendjaya.domain.player.service.StreamingService
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.BottomNavigationBar
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.Navigation
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.StreamingViewModel
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.WayGoldenDjayaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity(), StreamingCallback {

    private val streamingStateViewModel: StreamingStateViewModel by viewModels()

    @Inject
    lateinit var exoPlayer: ExoPlayer


    // digunakan untuk mengikat service dengan activity, belum digunakan
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as StreamingService.LocalBinder
            val streamingService = myBinder.getService()
            streamingService.setCallback(this@MainActivity)


        }

        override fun onServiceDisconnected(p0: ComponentName?) = Unit
    }

    fun bindService() {
        val serviceIntent = Intent(applicationContext, StreamingService::class.java)
        applicationContext.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }


    @UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val streamingStateViewModel: StreamingStateViewModel by viewModels()

        CoroutineScope(Dispatchers.Main).launch {
            streamingStateViewModel.urlFlow.collect() {

                prepareStreaming(it)

            }
        }



        setContent {
            WayGoldenDjayaTheme {


                MainScreen(streamingStateViewModel)
            }
        }
    }


    @UnstableApi
    @Composable
    fun MainScreen(streamingStateViewModel: StreamingStateViewModel) {


        val navController = rememberNavController()

        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            "login" -> false // bottom navbar disembunyikan ketika di halaman login
            else -> true // in all other cases show bottom bar
        }

        Scaffold(bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    items = BOTTOM_MENU_LIST,
                    navController = navController,
                    onItemClick = { item -> navController.navigate(item.route) }
                )
            }

        }, content = { it ->

            val padding = it
            Navigation(navController = navController, streamingStateViewModel, this)


        })
    }


    override fun onStartStreaming() {

        exoPlayer.prepare()

        exoPlayer.play()


    }

    override fun onStopStreaming() {
        exoPlayer.stop()


    }

    @UnstableApi
    override fun prepareStreaming(url: String) {

        exoPlayer.addListener(mediaListener())

        // Create a media item
        val mediaItem = MediaItem.fromUri(url)

        // Create a media source and pass the media item
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultHttpDataSource.Factory()
        )
            .createMediaSource(mediaItem)

        // Prepare the player with the media source
        exoPlayer.setMediaSource(mediaSource)

        streamingStateViewModel.readyStreamingState()


    }

    private fun mediaListener(): Player.Listener =
        object : Player.Listener {
            @SuppressLint("SwitchIntDef")
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {

                        if (streamingStateViewModel.playbackState.value == StreamingState.OnPlaying) {
                            streamingStateViewModel.bufferingStreamingState()


                        }


                    }
                    Player.STATE_READY -> {
                        if (streamingStateViewModel.playbackState.value == StreamingState.OnBuffering) {
                            exoPlayer.play()
                            streamingStateViewModel.playStreamingState()
                        }
                    }

                }
            }

            override fun onPlayerError(error: PlaybackException) {
                when (error.errorCode) {

                    PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS -> {
                        streamingStateViewModel.errorStreamingState("Sesi Streaming Telah Selesai")
                    }
                    PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> {
                        streamingStateViewModel.errorStreamingState("Koneksi Bermasalah, Mohon Periksa Koneksi Internet Anda..")
                    }
                    else -> {

                        streamingStateViewModel.errorStreamingState(error.errorCode.toString())


                    }


                }
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                if(isLoading){
                    streamingStateViewModel.loadingStreamingState()
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if(isPlaying){
                    streamingStateViewModel.playStreamingState()
                }else{
                    streamingStateViewModel.stopStreamingState()
                }
            }
        }
}



