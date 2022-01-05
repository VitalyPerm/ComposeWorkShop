package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class ConstraintLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutView()
        }
    }
}

@Composable
fun ConstraintLayoutView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        val (icon1, text1, icon2, text2, icon3, text3, icon4, text4, icon5, text5) = createRefs()
        Icon(
            painter = painterResource(id = R.drawable.ic_1), contentDescription = "1",
            modifier = Modifier
                .constrainAs(icon1) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(text1.start)
                    end.linkTo(text1.end)
                }
                .size(48.dp)
        )
        Text(text = "Icon one here!", modifier = Modifier.constrainAs(text1) {
            top.linkTo(parent.top, margin = 80.dp)
            start.linkTo(parent.start, margin = 24.dp)
        })

        Icon(
            painter = painterResource(id = R.drawable.ic_2), contentDescription = "1",
            modifier = Modifier
                .constrainAs(icon2) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(text2.start)
                    end.linkTo(text2.end)
                }
                .size(48.dp)
        )
        Text(text = "Icon two here!", modifier = Modifier.constrainAs(text2) {
            top.linkTo(parent.top, margin = 80.dp)
            end.linkTo(parent.end, margin = 24.dp)
        })

        Icon(
            painter = painterResource(id = R.drawable.ic_3), contentDescription = "1",
            modifier = Modifier
                .constrainAs(icon3) {
                    bottom.linkTo(text3.top, margin = 16.dp)
                    start.linkTo(text3.start)
                    end.linkTo(text3.end)
                }
                .size(48.dp)
        )
        Text(text = "Icon three here!", modifier = Modifier.constrainAs(text3) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Icon(
            painter = painterResource(id = R.drawable.ic_4), contentDescription = "1",
            modifier = Modifier
                .constrainAs(icon4) {
                    bottom.linkTo(text4.top, margin = 16.dp)
                    start.linkTo(text4.start)
                    end.linkTo(text4.end)
                }
                .size(48.dp)
        )
        Text(text = "Icon four here!", modifier = Modifier.constrainAs(text4) {
            start.linkTo(parent.start,  margin = 24.dp)
            bottom.linkTo(parent.bottom, margin = 24.dp)
        })

        Icon(
            painter = painterResource(id = R.drawable.ic_5), contentDescription = "1",
            modifier = Modifier
                .constrainAs(icon5) {
                    bottom.linkTo(text5.top, margin = 16.dp)
                    start.linkTo(text5.start)
                    end.linkTo(text5.end)
                }
                .size(48.dp)
        )
        Text(text = "Icon four here!", modifier = Modifier.constrainAs(text5) {
            end.linkTo(parent.end,  margin = 24.dp)
            bottom.linkTo(parent.bottom, margin = 24.dp)
        })
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintLayoutView()
}