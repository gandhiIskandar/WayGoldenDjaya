package com.binamultimediaindonesia.waygoldendjaya.representation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.binamultimediaindonesia.waygoldendjaya.domain.model.menu_model.BottomNavItem
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Primary,
        elevation = 5.dp

    ) {

        val backStackEntry = navController.currentBackStackEntryAsState()

        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Accent,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount != 0) {
                            BadgedBox(badge = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )

                            }

                        }else{
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }

                        if(selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }

                    }

                }
            )


        }

    }

}