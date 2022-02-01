package com.example.composeworkshop

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityView(
                toConstraint = { startActivityChooser(ConstraintLayout()) },
                toScaffold = { startActivityChooser(Scaffold()) },
                toCrossFade = { startActivityChooser(CrossFadeAnimation()) },
                toCanvas = { startActivityChooser(Canvas()) },
                toDropDown = { startActivityChooser(DropDown()) },
                toDropDownList = { startActivityChooser(DropDownList()) },
                toAnimation = { startActivityChooser(AnimationCard()) },
                toAnimationButton = { startActivityChooser(AnimatedButton()) },
                toCalendar = {startActivityChooser(Calendar())})
        }
    }

    private fun startActivityChooser(activity: ComponentActivity) {
        startActivity(Intent(this, activity::class.java))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    MainActivityView({}, {}, {}, {}, {}, {}, {}, {}, {})
}

@Composable
fun MainActivityView(
    toConstraint: () -> Unit,
    toScaffold: () -> Unit,
    toCrossFade: () -> Unit,
    toCanvas: () -> Unit,
    toDropDown: () -> Unit,
    toDropDownList: () -> Unit,
    toAnimation: () -> Unit,
    toAnimationButton: () -> Unit,
    toCalendar: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { toConstraint.invoke() }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp)
                    .align(CenterVertically)
            ) {
                Text(text = "Constraint\nLayout", textAlign = TextAlign.Center)
            }
            Button(
                onClick = { toScaffold.invoke() }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp)
            ) {
                Text(
                    text = "Scaffold\n", modifier = Modifier
                        .align(Alignment.Bottom)
                )
            }
            Button(
                onClick = { toCrossFade.invoke() }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp)
                    .align(CenterVertically)
            ) {
                Text(text = "CrossFade\nAnimation")
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 6.dp, end = 3.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { toCanvas.invoke() }) {
                Text(text = "Canvas")
            }

            Button(onClick = { toDropDown.invoke() }) {
                Text(text = "DropDown")
            }

            Button(onClick = { toDropDownList.invoke() }) {
                Text(text = "DropDownList")
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 6.dp, end = 3.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { toAnimation.invoke() }) {
                Text(text = "Animation")
            }
            Button(onClick = { toAnimationButton.invoke() }) {
                Text(text = "AnimationButton")
            }
            Button(onClick = { toCalendar.invoke() }) {
                Text(text = "Calendar")
            }
        }
    }
}