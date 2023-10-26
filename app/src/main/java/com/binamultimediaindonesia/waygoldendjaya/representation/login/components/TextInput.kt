package com.binamultimediaindonesia.waygoldendjaya.representation.login.components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Background
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Shapes

@Composable
fun MyTextInput (labelVal:String, type: KeyboardType, modifier: Modifier, trailingIcon: @Composable (()->Unit)? = null, textValue:MutableState<TextFieldValue> ) {


    TextField(
        modifier = modifier,
        shape = Shapes.large,
        label = {Text(text = labelVal)},
        value = textValue.value ,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            backgroundColor = Background,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = type),
        onValueChange = {
        textValue.value = it
    } )

}