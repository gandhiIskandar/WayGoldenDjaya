package com.binamultimediaindonesia.waygoldendjaya.representation.certificate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binamultimediaindonesia.waygoldendjaya.ActivityCallback
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Certificate
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary


@Composable
fun CertificateScreen (
    callback:ActivityCallback,
    modifier:Modifier = Modifier,
viewModel:CertificateViewModel = hiltViewModel()

) {

    val state = viewModel.state.value
    val token = viewModel.tokenState.value

    Surface {

        Column(modifier = modifier.fillMaxSize()) {
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
                                text = "Sertifikat Pelaksaan Umrah", style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.payton))
                                ), color = Color.White
                            )

                            Text(
                                text = "Land Arrangement Umroh and Hajj",
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

            Box(modifier = modifier.fillMaxSize()){

                state.data?.let{
                    Column(modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)

                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = "Terima Kasih atas Partisipasinya", style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.spectrallightitalic))
                            ), color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = modifier.height(10.dp))

                        Text(

                            text = "Silahkan klik tombol dibawah untuk download sertifikat umrah Anda", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Serif
                            ), color = Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = modifier.height(10.dp))
                        DataRow(entitas = "Kode", data = it.kode , modifier = modifier )
                        DataRow(entitas = "Nama", data = it.user.name , modifier = modifier )
                        DataRow(entitas = "Program", data = it.session.program.program_name , modifier = modifier )
                        DataRow(entitas = "Sesi", data = it.session.end_date , modifier = modifier )

                        Spacer(modifier = modifier.height(10.dp))

                        Button(
                            modifier = modifier
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Primary, contentColor = Color.White
                            ),
                            onClick = {

                                callback.downloadData(
                                    it.url,
                                    token
                                )

                            }
                        ){
                            Text(
                                text = "Download",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_download_24),
                                contentDescription = "Update"
                            )
                        }


                    }

                }

                if (state.error.isNotBlank()) {

                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
                }




            }


        }

    }
}

@Composable
fun DataRow(entitas:String,data: String, modifier:Modifier){

    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){

        Text(modifier=modifier.fillMaxWidth(0.3f)
            ,text=entitas)
        Text(modifier=modifier.fillMaxWidth(0.1f)
            ,text=":", textAlign = TextAlign.Center)
        Text(modifier=modifier.fillMaxWidth()
            ,text=data)


    }
}

@Composable
@Preview(showBackground = true)
fun CertificateScreenPrev(modifier: Modifier = Modifier)
{

}
