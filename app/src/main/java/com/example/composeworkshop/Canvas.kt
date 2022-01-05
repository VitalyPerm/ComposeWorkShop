package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class Canvas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasView()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CanvasPreview() {
    CanvasView()
}

@Composable
fun CanvasView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        val (rect, rectText, circle, circleText, arc, arcText,
            line, lineText) = createRefs()
        Text(
            text = "Rect",
            modifier = Modifier
                .constrainAs(rectText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 270.dp),
        )
        Canvas(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(rect) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        ) {
            drawRect(
                Color.Blue,
                topLeft = Offset(0f, 0f),
                size = Size(450f, 355f)
            )
        }

        Text(text = "Circle", modifier = Modifier
            .constrainAs(circleText) {
                top.linkTo(circle.bottom)
                start.linkTo(parent.start)
            }
            .padding(top = 80.dp, start = 120.dp))
        Canvas(modifier = Modifier
            .constrainAs(circle) {
                top.linkTo(rect.bottom)
                start.linkTo(rect.start)
            }
            .padding(top = 150.dp)) {
            drawCircle(Color.Red, center = Offset(120f, 250f), radius = 160f)
        }

        Text(text = "Arc", modifier = Modifier
            .constrainAs(arcText) {
                start.linkTo(arc.start)
                top.linkTo(arc.top)
            }
            .padding(top = 180.dp, start = 30.dp))
        Canvas(modifier = Modifier
            .constrainAs(arc) {
                top.linkTo(circle.bottom)
                start.linkTo(circle.start)
            }
            .padding(top = 150.dp)
        ) {
            drawArc(
                Color.Yellow, 0f, 120f, useCenter = true,
                size = Size(300f, 300f),
                topLeft = Offset(0f, 0f)
            )
        }

        Text(text = "Line", modifier = Modifier
            .constrainAs(lineText) {
                start.linkTo(line.start)
                top.linkTo(line.top)
            }
            .padding(top = 200.dp))

        Canvas(modifier = Modifier
            .constrainAs(line) {
                top.linkTo(arc.bottom)
                start.linkTo(arc.start)
            }
            .padding(top = 130.dp)) {
            drawLine(
                Color.Black, Offset(0f, 50f), Offset(300f, 600f),
                strokeWidth = 10f
            )
        }
    }
}

