package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

class DropDownList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var expandedFirst by remember { mutableStateOf(true) }
            var expandedSecond by remember { mutableStateOf(true) }
            var expandedThird by remember { mutableStateOf(true) }
            val rotateX = animateFloatAsState(
                targetValue = if (expandedThird) 0f else -90f,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Колонна 1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Cyan
                        )
                        .padding(20.dp)
                        .wrapContentSize(Alignment.Center)
                        .noRippleClickable { expandedFirst = !expandedFirst }
                )
                if (expandedFirst) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                    }
                }

                Text(
                    text = "Колонна 2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Yellow
                        )
                        .padding(20.dp)
                        .clickable { expandedSecond = !expandedSecond }
                )
                if (expandedSecond) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                    }
                }

                Text(
                    text = "Колонна 3",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Green
                        )
                        .padding(20.dp)
                        .clickable { expandedThird = !expandedThird }
                )
                if (expandedThird) {
                    LazyColumn(
                        modifier = Modifier
                            .background(Color.Cyan)
                            .fillMaxWidth()
                            .graphicsLayer {
                                transformOrigin = TransformOrigin(0.5f, 0f)
                                rotationX = rotateX.value
                            },
                    ) {
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                        item { Text(text = "Gintarinis!") }
                    }
                }
            }
        }

    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}