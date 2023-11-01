package com.binamultimediaindonesia.waygoldendjaya.representation.streaming


import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment

import androidx.hilt.navigation.compose.hiltViewModel

import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.common.Constants
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.components.PlayStreaming
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment


@Composable
fun StreamingScreen(
    streamingDataViewModel: StreamingViewModel = hiltViewModel(),

    ) {

    val context = LocalContext.current

    val state = streamingDataViewModel.state.value

    val user = streamingDataViewModel.userData.value





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

           // PlayStreaming(modifier = Modifier)

            Box(modifier = Modifier.fillMaxSize()) {






                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }


        }
    }
}




private fun setStreamingFragment(): Fragment {
    val appID: Long = Constants.APP_ID
    val appSign: String = Constants.APP_SIGN
    val roomID: String = "1234"
    val userID: String = "1456"
    val userName: String = "1456"
    val isHost = true

    val config: ZegoUIKitPrebuiltLiveAudioRoomConfig = if (isHost) {
        ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
    } else {
        ZegoUIKitPrebuiltLiveAudioRoomConfig.audience()
    }


    return ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(
        appID, appSign, userID, userName, roomID, config
    )
}


@Preview
@Composable
fun StreamingScreenPreview() {


}