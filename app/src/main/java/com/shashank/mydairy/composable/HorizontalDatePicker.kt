package com.shashank.mydairy.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorizontalDatePicker(
    modifier: Modifier = Modifier,
    selectedData: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
) {
    val today = LocalDate.now()
    val daysRange = remember {
        (0..7).map {
            today.plusDays(it.toLong())
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(daysRange) { date ->
            val isSelectedDate = date == selectedData
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .background(if (isSelectedDate) Color(0xFF87CEEB) else Color.LightGray)
                    .border(1.dp, if (isSelectedDate) Color(0xFF87CEEB) else Color.LightGray)
                    .clickable {
                        onDateSelected(date)
                    }
                    .padding(15.dp)
            ) {
                Text(
                    text = date.dayOfMonth.toString(), style = MaterialTheme.typography.titleMedium,
                    color = if (isSelectedDate) Color.White else Color.Black
                )
                Text(
                    text = date.dayOfWeek.name.take(3),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSelectedDate) Color.White else Color.Black
                )
            }
        }
    }
}




