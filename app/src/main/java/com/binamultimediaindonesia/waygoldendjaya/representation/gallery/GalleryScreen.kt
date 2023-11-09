package com.binamultimediaindonesia.waygoldendjaya.representation.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GalleryScreen() {
    Surface {
        Box( modifier= Modifier.fillMaxSize()
        ){
            Text(modifier= Modifier.align(Alignment.Center), text="Konten Sedang Dalam Pengembangan")
        }

    }
}