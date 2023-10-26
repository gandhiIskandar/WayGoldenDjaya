package com.binamultimediaindonesia.waygoldendjaya.representation.streaming

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.binamultimediaindonesia.waygoldendjaya.R
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binamultimediaindonesia.waygoldendjaya.domain.player.StreamingState
import com.binamultimediaindonesia.waygoldendjaya.domain.player.StreamingStateViewModel
import com.binamultimediaindonesia.waygoldendjaya.domain.player.callback.StreamingCallback
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.components.PlayStreaming
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import javax.inject.Inject


@UnstableApi
@Composable
fun StreamingScreen(
    streamingDataViewModel: StreamingViewModel = hiltViewModel(),
    streamingStateViewModel: StreamingStateViewModel,
    streamingCallback: StreamingCallback


) {
    val state = streamingDataViewModel.state.value

    val streamingState = streamingStateViewModel.playbackState

    val url = streamingStateViewModel.urlFlow.collectAsState().value

    if(url == "" ){

        state.data?.let {
            streamingStateViewModel.updateUrl(it.url)

        }


    }





    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .graphicsLayer(
                        clip = true,
                        shape = RoundedCornerShape(bottomStart = 40.dp)
                    )
                    .paint(
                        painterResource(
                            id = R.drawable.background
                        ),
                        contentScale = ContentScale.Crop
                    )

                    .padding(horizontal = 25.dp)
                    .padding(top = 25.dp)
                    .padding(bottom = 5.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_grid),
                            contentDescription = "icon logo",
                            tint = Accent
                        )

                        Spacer(modifier = Modifier.width(15.dp))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {

                            Text(
                                text = "Siaran Muthawwif", style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.payton))
                                ), color = Color.White
                            )





                            Text(
                                text = "Silahkan Menyimak Siaran Sesuai dengan Group",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 14.sp,

                                    )
                            )

                            Text(
                                text = "Way Golden Djaya Group",
                                color = Accent,
                                style = TextStyle(
                                    fontSize = 13.sp,

                                    )
                            )


                        }


                    }

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            PlayStreaming(modifier = Modifier)



            Box(modifier = Modifier.fillMaxSize()) {


                Button(
                    onClick = {

                        when(streamingState.value){

                            is StreamingState.OnPlaying -> {
                                streamingCallback.onStopStreaming()
                            }
                            is StreamingState.OnReady -> {
                                streamingCallback.onStartStreaming()
                            }
                            is StreamingState.OnStop -> {
                                streamingCallback.onStartStreaming()
                            }
                            is StreamingState.OnError->{
                                streamingCallback.onStartStreaming()
                            }
                            else -> {}
                        }





                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .align(Alignment.TopCenter),
                    colors = ButtonDefaults.buttonColors(Primary)
                ) {

                when(streamingState.value){
                    is StreamingState.OnReady -> {

                        Text(text = "Mulai Streaming")
                    }
                    is StreamingState.OnPlaying -> {

                        Text(text = "Stop Streaming")

                    }
                    is StreamingState.OnStop -> {
                        Text(text = "Mulai Streaming")
                    }
                    is StreamingState.OnLoading->{
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = Color.White
                    )
                    }
                    is StreamingState.OnBuffering ->{
                        CircularProgressIndicator(
                            modifier = Modifier.size(25.dp),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Buffering...")
                        
                    }
                    is StreamingState.OnError -> {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh" )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Coba Lagi..")

                    }
                    else -> {}
                }

                }

            }

            if(streamingState.value is StreamingState.OnError){

                val error = streamingState.value as StreamingState.OnError

                val message = error.message

                Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
            }

        }
    }
}





@Preview
@Composable
fun StreamingScreenPreview() {


}