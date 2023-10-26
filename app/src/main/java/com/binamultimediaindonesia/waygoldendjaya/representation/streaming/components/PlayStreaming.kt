package com.binamultimediaindonesia.waygoldendjaya.representation.streaming.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.representation.home.CoilImage
import com.binamultimediaindonesia.waygoldendjaya.representation.home.components.MultiColorText
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun PlayStreaming(modifier: Modifier) {
    Box(modifier = modifier.padding(
        horizontal = 25.dp
    )){


    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Primary)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )

    {

        Column(
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic__broadcast
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = modifier.width(5.dp))
                Text(
                    text = "Siaran Group 1",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,

                    )
                )

            }

            Spacer(
                modifier = modifier.height(10.dp)
            )

            Column(
                modifier = Modifier

                    .heightIn(min = 50.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                // nanti diisi sesuai dengan nama user
                Text(
                    text = "Ustadz ABC", style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Accent
                    )
                )





                Text(text = "Makah Almukaromah No. 08", color = Color.White)


            }


        }

        Image(
            painter = painterResource(id = R.drawable.sample_user), contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),
            contentScale = ContentScale.Crop
        )


    }
    }

}

@Preview
@Composable
fun PlayStreamingPreview() {

    PlayStreaming(modifier = Modifier)

}