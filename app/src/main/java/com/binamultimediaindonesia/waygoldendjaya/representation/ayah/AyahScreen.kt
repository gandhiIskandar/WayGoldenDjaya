package com.binamultimediaindonesia.waygoldendjaya.representation.ayah

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.components.AyahItem
import com.binamultimediaindonesia.waygoldendjaya.representation.surah.components.SurahItem
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent

@Composable
fun AyahScreen(modifier: Modifier,
               viewModel: AyahViewModel= hiltViewModel()) {

    val state = viewModel.state.value

    val surat = state.data?.data

    Box{
        Column(modifier = modifier.fillMaxSize()){

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

                    .padding(25.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(28.dp)
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Column(
                            modifier = Modifier
                                .width(230.dp)
                                .heightIn(min = 50.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            // nanti diisi sesuai dengan nama user
                            Text(text = surat?.namaLatin?:"Terjadi Kesalahan", style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.payton))
                            ), color = Color.White)


                            Text(text = "${surat?.jumlahAyat} Ayat", color = Accent)


                        }

                        Image(
                            painter = painterResource(id = R.drawable.ic__alquran),
                            contentDescription = "Gambar alquran" )

                    }

                }

            }
         surat?.ayat?.let{ ayah ->

                LazyColumn{
                    items(ayah){ each->
                        AyahItem(modifier, each)
                    }
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

@Preview(showBackground = true)
@Composable
fun AyahScreenPreview() {

    AyahScreen(modifier = Modifier)
}