package com.vadym.upwind.ui.home.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.sp
import com.vadym.upwind.ui.theme.Pink200

@Composable
fun SunStatus(
    sunrise: String,
    sunset: String,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawPath(
            path = Path().apply {
                moveTo(
                    x = 0f,
                    y = size.height * 0.35f
                )
                cubicTo(
                    x1 = size.width / 2,
                    y1 = size.height * 0.45f,
                    x2 = size.width / 2,
                    y2 = size.height * 0.75f,
                    x3 = size.width,
                    y3 = size.height * 0.75f
                )
            },
            brush = Brush.horizontalGradient(
                listOf(Color.Yellow, Pink200, Color.LightGray)
            ),
            style = Stroke(width = 4f)
        )
        //Sun with shadow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Yellow, Color.Transparent),
                center = Offset(
                    x = size.width * 0.1f,
                    y = size.height * 0.2f
                ),
                radius = size.minDimension * 0.15f
            ),
            radius = size.minDimension * 0.15f,
            center = Offset(
                x = size.width * 0.1f,
                y = size.height * 0.2f
            ),
        )
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color.Yellow, Color.Magenta),
                start = Offset(
                    x = 0f,
                    y = 0f
                ),
                end = Offset(
                    x = size.width * 0.25f,
                    y = size.height * 0.35f
                )
            ),
            radius = size.minDimension * 0.1f,
            center = Offset(
                x = size.width * 0.1f,
                y = size.height * 0.2f
            )
        )
        //Moon with shadow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.LightGray, Color.Transparent),
                center = Offset(
                    x = size.width * 0.9f,
                    y = size.height * 0.5f
                ),
                radius = size.minDimension * 0.15f
            ),
            radius = size.minDimension * 0.15f,
            center = Offset(
                x = size.width * 0.9f,
                y = size.height * 0.5f
            )
        )
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color.White, Color.Yellow.copy(0.5f)),
                start = Offset(
                    x = size.width * 0.8f,
                    y = size.height * 0.4f
                ),
                end = Offset(
                    x = size.width,
                    y = size.height * 0.7f
                )
            ),
            radius = size.minDimension * 0.1f,
            center = Offset(
                x = size.width * 0.9f,
                y = size.height * 0.5f
            )
        )
        drawIntoCanvas {
            it.nativeCanvas.drawText(
                sunrise,
                size.width * 0.2f,
                size.height * 0.23f,
                Paint().apply {
                    isAntiAlias = true
                    textSize = 16.sp.toPx()
                    color = android.graphics.Color.WHITE
                }
            )
            it.nativeCanvas.drawText(
                sunset,
                size.width * 0.6f,
                size.height * 0.53f,
                Paint().apply {
                    isAntiAlias = true
                    textSize = 16.sp.toPx()
                    color = android.graphics.Color.WHITE
                }
            )
        }
    }
}