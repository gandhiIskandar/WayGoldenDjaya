package com.binamultimediaindonesia.waygoldendjaya.representation.home.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent

@Composable
fun MultiColorText (text: String) {

    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Accent )) {
            append("Halo, ")
        }
        append(text)
    }, style = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.payton))
    ), color = Color.White)


}