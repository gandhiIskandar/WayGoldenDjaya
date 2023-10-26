package com.binamultimediaindonesia.waygoldendjaya.representation.schedule.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.common.Util.showHourInIndonesia
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import java.util.*


@Composable
fun ScheduleItem(schedule :Schedule) {

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {

        Text(text = showHourInIndonesia(schedule.date),
            style = TextStyle(
                color = Primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            )
        Spacer(Modifier.width(20.dp))

        Text(text = schedule.description,
            style = TextStyle(
                color = Primary,
                fontSize = 16.sp
            )
        )



    }

}

@Preview(showBackground = true)
@Composable
fun ScheduleItemPreview() {

}