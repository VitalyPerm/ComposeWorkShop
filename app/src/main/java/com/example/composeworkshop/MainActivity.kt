package com.example.composeworkshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
                toCrossFade = { startActivityChooser(CrossFadeAnimation()) })
        }
    }

    fun startActivityChooser(activity: ComponentActivity) {
        startActivity(Intent(this, activity::class.java))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    MainActivityView({}, {}, {})
}

@Composable
fun MainActivityView(
    toConstraint: () -> Unit,
    toScaffold: () -> Unit,
    toCrossFade: () -> Unit,
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
    }
}