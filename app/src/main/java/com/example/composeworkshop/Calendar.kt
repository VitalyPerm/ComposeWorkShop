package com.example.composeworkshop

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate
import java.time.YearMonth

class Calendar : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalendarView()
        }
    }
}

@Composable
fun CalendarView() {
    val calendarState =
        rememberSelectableCalendarState(initialSelectionMode = SelectionMode.Period)
    SelectableCalendar(calendarState = calendarState, modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp),
        dayContent = { DefaultDayCustom(state = it)}
    )
}

@Composable
fun <T : SelectionState> DefaultDayCustom(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    selectionColor: Color = MaterialTheme.colors.secondary,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)


        Box(
            modifier = Modifier.clickable {
                onClick(date)
                selectionState.onDateSelected(date)
            }
                .aspectRatio(1f)
                .padding(2.dp)
                .clip(CircleShape)
                .background(if(isSelected) selectionColor else Color.White)
                ,
            contentAlignment = Alignment.Center,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
}

/*
        contentColor = if (isSelected) selectionColor else contentColorFor(
            backgroundColor = MaterialTheme.colors.surface
        )
 */

@Preview
@Composable
fun CalendarPreview() {
    CalendarView()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomSelectionSample() {
    Calendar(calendarState = rememberMonthSelectionState())
}

private class MonthSelectionState(
    initialSelection: YearMonth? = null,
) : SelectionState {

    private var selection by mutableStateOf(initialSelection)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun isDateSelected(date: LocalDate): Boolean =
        date.yearMonth == selection

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSelected(date: LocalDate) {
        selection = if (date.yearMonth == selection) null else date.yearMonth
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @Suppress("FunctionName") // Factory function
        fun Saver(): Saver<MonthSelectionState, Any> = Saver(
            save = { it.selection?.toString() },
            restore = { restored ->
                val selection = (restored as? String)?.let { YearMonth.parse(it) }
                MonthSelectionState(selection)
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun rememberMonthSelectionState(
    initialMonth: YearMonth = YearMonth.now(),
    initialSelection: YearMonth? = null,
    monthState: MonthState = rememberSaveable(saver = MonthState.Saver()) {
        MonthState(initialMonth = initialMonth)
    },
    selectionState: MonthSelectionState = rememberSaveable(saver = MonthSelectionState.Saver()) {
        MonthSelectionState(initialSelection = initialSelection)
    }
): CalendarState<MonthSelectionState> = remember { CalendarState(monthState, selectionState) }

private val LocalDate.yearMonth: YearMonth
    @RequiresApi(Build.VERSION_CODES.O)
    get() = YearMonth.of(year, month)

