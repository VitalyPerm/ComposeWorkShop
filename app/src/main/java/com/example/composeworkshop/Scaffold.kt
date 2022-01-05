package com.example.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Scaffold : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldView()
        }
    }
}


@ExperimentalMaterialApi
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ScaffoldPreview() {
    ScaffoldView()
}


@ExperimentalMaterialApi
@Composable
fun ScaffoldView() {
    val color = Color.Cyan
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TopAppBar") },
                backgroundColor = color,
                navigationIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_1), contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                scope.launch {
                                    scaffoldState.drawerState.animateTo(
                                        DrawerValue.Open,
                                        tween(1000)
                                    )
                                }
                            })
                })
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) { Text(text = "X") }
        },
        drawerContent = { Text(text = "Drawer Content") },
        content = { Text(text = "Body Content") },
        bottomBar = {
            BottomAppBar(Modifier.background(color = color)) {
                Text(text = "BottomAppBar")
            }
        }
    )
}