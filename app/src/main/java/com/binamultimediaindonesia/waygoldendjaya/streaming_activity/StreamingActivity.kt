package com.binamultimediaindonesia.waygoldendjaya.streaming_activity


import android.os.Bundle
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.audio_room.AudioRoomBackgroundView
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.APP_ID
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.APP_SIGN
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toUser
import com.binamultimediaindonesia.waygoldendjaya.common.Util.toastShort
import com.binamultimediaindonesia.waygoldendjaya.databinding.ActivityStreamingBinding
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.components.PlayStreaming
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.WayGoldenDjayaTheme
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment
import com.zegocloud.uikit.prebuilt.liveaudioroom.core.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreamingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStreamingBinding

    private lateinit var userData: User
    private lateinit var muthawiff: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStreamingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: StreamingViewModel by viewModels()

        viewModel.performInit {
            userData = it[0]
            muthawiff = it[1]


            binding.composeView.apply {


                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                setContent {
                    WayGoldenDjayaTheme {

                        Surface {
                            Column {

                                if (viewModel.state.value != ""){
                                    Toast.makeText(context, viewModel.state.value, Toast.LENGTH_SHORT).show()
                                }

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

                                PlayStreaming(
                                    modifier = Modifier,
                                    muthawiff,
                                    ImageRequest.Builder(context),
                                   userData.group.group_name
                                )


                            }
                        }

                    }
                }

            }

            addFragment(userData)

        }


    }


    private fun addFragment(userData:User) {


        val appID: Long = APP_ID
        val appSign: String = APP_SIGN
        val roomID: String = userData.group_id.toString()
        val userID: String = userData.pin
        val userName: String = userData.name
        val isHost = userData.is_leader
        val userUrl = userData.profile_image


        val config: ZegoUIKitPrebuiltLiveAudioRoomConfig
        if (isHost) {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
            config.takeSeatIndexWhenJoining = 0


            config.userAvatarUrl = userUrl


        } else {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.audience()
            config.userAvatarUrl = userUrl

        }


        val confirmDialogInfo = ZegoDialogInfo()
        confirmDialogInfo.title = "Meninggalkan Ruangan"
        confirmDialogInfo.message = "Yakin ingin meninggal ruangan?"
        confirmDialogInfo.cancelButtonName = "Tidak"
        confirmDialogInfo.confirmButtonName = "Ya"
        config.confirmDialogInfo = confirmDialogInfo

        config.hostSeatIndexes = listOf(0)
        config.layoutConfig.rowConfigs = listOf(
            ZegoLiveAudioRoomLayoutRowConfig(1, ZegoLiveAudioRoomLayoutAlignment.CENTER)
        )


        val fragment = ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(
            appID, appSign, userID, userName, roomID, config
        )




        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()

        val roomBackgroundView = AudioRoomBackgroundView(this)
        roomBackgroundView.setBackgroundResource(R.color.white)
        fragment.setBackgroundView(roomBackgroundView)


    }

}



