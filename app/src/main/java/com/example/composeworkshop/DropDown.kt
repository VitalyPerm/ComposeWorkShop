package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class DropDown : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var expanded by remember { mutableStateOf(true) }
            var expanded2 by remember { mutableStateOf(true) }
            val items = listOf("A", "B", "C", "D", "E", "F")
            val disabledValue = "B"
            var selectedIndex by remember { mutableStateOf(0) }
            Column(Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .wrapContentSize(Alignment.TopStart)) {
                    Text(
                        items[selectedIndex], modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { expanded = true })
                            .background(
                                Color.Gray
                            )
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.Red
                            )
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                selectedIndex = index
                                expanded = false
                            }) {
                                val disabledText = if (s == disabledValue) {
                                    " (Disabled)"
                                } else {
                                    ""
                                }
                                Text(text = s + disabledText)
                            }
                        }
                    }
                }
                Box(modifier = Modifier
                    .wrapContentSize(Alignment.TopStart).padding(top = 30.dp)) {
                    Text(
                        items[selectedIndex], modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { expanded2 = true })
                            .background(
                                Color.Cyan
                            )
                    )
                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded2 = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.Red
                            )
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                selectedIndex = index
                                expanded2 = false
                            }) {
                                val disabledText = if (s == disabledValue) {
                                    " (ХУЙ)"
                                } else {
                                    "2 ХУЯ"
                                }
                                Text(text = s + disabledText)
                            }
                        }
                    }
                }
            }

        }
    }
}