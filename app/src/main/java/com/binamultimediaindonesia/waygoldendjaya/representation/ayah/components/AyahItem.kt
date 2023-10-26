package com.binamultimediaindonesia.waygoldendjaya.representation.ayah.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Ayah
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary


@Composable
fun AyahItem(modifier: Modifier, ayah: Ayah) {
    Box(modifier = modifier
        .padding(horizontal = 25.dp, vertical = 10.dp)
        .drawBehind {
            val strokeWidth = 1.dp.toPx()
            val y = size.height - strokeWidth / 2
            drawLine(
                Primary,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidth,
                alpha = 0.5f
            )
        }


    ){


        Row(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            ){


            Box(
                modifier = modifier
                    .border(width = 1.dp, color = Primary, shape = CircleShape)
                    .clip(CircleShape)

            ){
                Text(modifier = modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    text = ayah.nomorAyat.toString())
            }

            Spacer(modifier = modifier.width(5.dp))

            Column {
                Text(text = ayah.teksArab)
                Text(text= ayah.teksIndonesia,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light)
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun AyahItemPreview() {

   // AyahItem(Modifier)


}