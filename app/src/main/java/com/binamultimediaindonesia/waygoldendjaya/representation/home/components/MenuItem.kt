package com.binamultimediaindonesia.waygoldendjaya.representation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.binamultimediaindonesia.waygoldendjaya.domain.model.menu_model.MenuModel
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Shapes

@Composable
fun MenuItem(menu: MenuModel, onClick:() -> Unit) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke() })
            .padding(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Card(
            backgroundColor = Primary,
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp),
            elevation = 8.dp,
            shape = Shapes.large

        ) {
            Icon(
                painter= painterResource(id = menu.icon),
                modifier = Modifier.padding(15.dp),
                contentDescription = null,
                tint = Color.White
            )

        }

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(id = menu.text ),
            style = TextStyle(
                color = Primary
            )
        )

    }
}