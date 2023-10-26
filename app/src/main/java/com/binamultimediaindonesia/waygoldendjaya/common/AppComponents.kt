package com.binamultimediaindonesia.waygoldendjaya.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextComponent(value:String, color: Color) {

    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal

        ),
        textAlign = TextAlign.Center,
        color = color,

    )

}

@Composable
fun SmallText(value: String, color:Color){
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            color = color,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal

        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun BigTextComponent(value: String, color:Color){
  Text(  text = value,
    modifier = Modifier.fillMaxWidth(),
    style = TextStyle(
        color = color,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal

    ),
    textAlign = TextAlign.Center
    )
}