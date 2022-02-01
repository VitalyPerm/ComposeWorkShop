package com.example.composeworkshop

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import java.time.DayOfWeek
import java.time.DayOfWeek.SUNDAY
import java.time.format.TextStyle.NARROW
import java.util.Locale

@Composable
fun CustomComponentsSample() {
    StaticCalendar(
        modifier = Modifier.animateContentSize(),
        showAdjacentMonths = false,
        firstDayOfWeek = SUNDAY,
        monthContainer = { MonthContainer(it) },
        dayContent = { DayContent(dayState = it) },
        weekHeader = { WeekHeader(daysOfWeek = it) },
        monthHeader = { MonthHeader(monthState = it) },
    )
}

@Composable
private fun DayContent(dayState: NonSelectableDayState) {
    Text(
        text = dayState.date.dayOfMonth.toString(),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
    )
}

@Composable
private fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
    Row {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(NARROW, Locale.ROOT),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
private fun MonthHeader(monthState: MonthState) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Text(convertMonth(monthState.currentMonth.month.value), style = MaterialTheme.typography.h3)
    }
}

fun convertMonth(month: Int): String = when (month) {
    1 -> "Январь"
    2-> "Февраль"
    else -> "WTF"
}

@Composable
private fun MonthContainer(content: @Composable (PaddingValues) -> Unit) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        content = { content(PaddingValues(4.dp)) },
    )
}

@Preview
@Composable
private fun CustomComponentsPreview() {
    MaterialTheme {
        Surface {
            CustomComponentsSample()
        }
    }
}