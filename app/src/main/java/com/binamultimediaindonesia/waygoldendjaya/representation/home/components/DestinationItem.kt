package com.binamultimediaindonesia.waygoldendjaya.representation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun DestinationItem(destination: Destination, model:ImageRequest.Builder) {
    Box(modifier = Modifier.padding(5.dp)) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = painterResource(id = destination.image),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .size(130.dp),
//                contentScale = ContentScale.Crop
//            )

            CoilImage(model = model, url = destination.image )

            Box(modifier = Modifier.width(120.dp)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = destination.destination_name, style = TextStyle(
                        color = Primary,

                        fontSize = 17.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "ikon wisata",
                    tint = Color.Gray

                )
                Text(
                    text = destination.city, style = TextStyle(
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                )
            }


        }

    }
}

@Composable
fun CoilImage(model: ImageRequest.Builder, url: String) {
    AsyncImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp)),
        model = model
            .data(url)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "Profile Image"
    )
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {




}