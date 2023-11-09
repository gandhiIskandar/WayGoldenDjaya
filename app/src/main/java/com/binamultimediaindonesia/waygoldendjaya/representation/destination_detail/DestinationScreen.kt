package com.binamultimediaindonesia.waygoldendjaya.representation.destination_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.binamultimediaindonesia.waygoldendjaya.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.binamultimediaindonesia.waygoldendjaya.common.Util
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun DestinationScreen(
    modifier: Modifier = Modifier,
    viewModel: DestinationViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val context = LocalContext.current
    val model = ImageRequest.Builder(context)

    Surface {

        state.destination?.let { destination ->

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState())
            )
            {


                    Text(modifier= modifier.align(Alignment.CenterHorizontally),text = destination.destination_name, style = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ))
                    Text(modifier= modifier.align(Alignment.CenterHorizontally),text = destination.city, style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                        )


                Spacer(modifier = modifier.height(20.dp))

                    Row() {
                        CoilImageDest(model = model, url = destination.image)
                        Text(
                            modifier= modifier.padding(horizontal = 10.dp),
                            text = destination.description,style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Justify
                        ))

                    }

                Spacer(modifier = modifier.height(20.dp))

                Text(
                    modifier= modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.kunjungan),style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.LightGray,
                        textAlign = TextAlign.Center
                    ))

                Button(
                    onClick = {

                              intentWhatsapp(context)
                       
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Primary, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.5f)
                        .height(50.dp)
                ) {

                  Icon(imageVector = Icons.Default.Phone, contentDescription ="" )

                    Spacer(modifier = modifier.width(10.dp))
                    
                    Text(text = "WhatsApp", color = Color.White)


                }


            }

        }


    }
}

@Composable
fun CoilImageDest(model: ImageRequest.Builder, url: String) {
    AsyncImage(
        model = model
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(10.dp)),

        contentScale = ContentScale.Crop,
        contentDescription = "Profile Image"
    )
}

fun intentWhatsapp(context:Context){
    // Ganti "1234567890" dengan nomor telepon tujuan di WhatsApp
    val phoneNumber = "+6281999912146"

    // Membentuk URI untuk membuka aplikasi WhatsApp dengan nomor telepon yang dituju
    val uri = Uri.parse("smsto:$phoneNumber")

    // Membuat Intent
    val intent = Intent(Intent.ACTION_SENDTO, uri)
    intent.setPackage("com.whatsapp") // Package name untuk WhatsApp

    try{
        context.startActivity(intent)

    }catch (e: Exception){
        Toast.makeText(context,"WhatsApp Tidak Terinstall di Handphone Anda", Toast.LENGTH_LONG).show()
    }


}



