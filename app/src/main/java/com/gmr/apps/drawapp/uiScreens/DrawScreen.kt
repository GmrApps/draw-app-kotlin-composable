package com.gmr.apps.drawapp.uiScreens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmr.apps.drawapp.model.Line

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DrawScreen() {
    val lines = remember { mutableStateListOf<Line>() }
    var currentColor by remember { mutableStateOf(Color.Black) }


    Column {


        TopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary),
            title = {
                Text("Color Picker:",
                    color = Color.White,
                    fontSize = 18.sp
                )
            },
            actions = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { currentColor = Color.Black },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) {
                    // Black button
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    onClick = { currentColor = Color.Red },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) {
                    // Black button
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = { currentColor = Color.Green },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) {
                    // Red button
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = { currentColor = Color.Blue },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) {
                    // Blue button
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { lines.clear() },
                    modifier = Modifier.padding(3.dp)
                ) {
                    Text(text = "Clear")
                }
            }
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        val line = Line(
                            start = change.position - dragAmount,
                            end = change.position,
                            color = currentColor
                        )

                        lines.add(line)
                    }
                }
        ) {
            lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth,
                    cap = StrokeCap.Round
                )
            }
        }

    }
}

