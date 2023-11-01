package com.binamultimediaindonesia.waygoldendjaya



import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
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
import com.binamultimediaindonesia.waygoldendjaya.audio_room.AudioRoomBackgroundView
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.APP_ID
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.APP_SIGN
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toUser
import com.binamultimediaindonesia.waygoldendjaya.databinding.ActivityStreamingBinding
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.components.PlayStreaming
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.WayGoldenDjayaTheme
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment
import com.zegocloud.uikit.prebuilt.liveaudioroom.core.*


class StreamingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStreamingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStreamingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.apply {

           val muthawif = mutableStateOf(intent.getStringExtra("muthawif")!!.toUser())



            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                WayGoldenDjayaTheme {

                    Surface {
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

                            PlayStreaming(modifier = Modifier, muthawif.value )


                        }
                    }

                }
            }

        }

        addFragment()


    }


    private fun addFragment() {



        val appID: Long = APP_ID
        val appSign: String = APP_SIGN
        val roomID: String = intent.getStringExtra("room")!!
        val userID: String = intent.getStringExtra("userid")!!
        val userName: String = intent.getStringExtra("username")!!
        val isHost = intent.getBooleanExtra("host", false)

        val config: ZegoUIKitPrebuiltLiveAudioRoomConfig
        if (isHost) {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
           config.takeSeatIndexWhenJoining = 0


        } else {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.audience()

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



