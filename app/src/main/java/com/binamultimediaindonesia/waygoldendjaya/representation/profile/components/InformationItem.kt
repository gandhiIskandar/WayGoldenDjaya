package com.binamultimediaindonesia.waygoldendjaya.representation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun InformationItem(text:String, icon:ImageVector) {

    Row(
        modifier = Modifier

            .fillMaxWidth()
            .padding(vertical = 15.dp)
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(

            imageVector = icon,
            contentDescription = text,
            tint = Primary
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Primary
            ),
        )

        Icon(

            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.LightGray
        )

    }
}