package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CrossFadeAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrossFadeAnim()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrossFadeAnimPreview() {
    CrossFadeAnim()
}


enum class Colors(val color: Color) {
    Red(Color.Red), Cyan(Color.Cyan), White(Color.White)
}

@Composable
fun CrossFadeAnim() {
    var currentColor by remember { mutableStateOf(Colors.Red) }
    Column {
        Row {
            Colors.values().forEach { colors ->
                Button(
                    onClick = { currentColor = colors },
                    Modifier
                        .weight(1f, true)
                        .height(48.dp)
                        .background(colors.color),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.color)
                ) {
                    Text(colors.name)
                }
            }
        }
        Crossfade(targetState = currentColor, animationSpec = tween(2000)) { selectedColor ->
            Box(modifier = Modifier
                .fillMaxSize()
                .background(selectedColor.color))
        }
    }
}