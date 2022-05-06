package com.vadym.upwind.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BigCloud(
    cloudAlpha: Float = 0.85f,
    color: Color,
    width: Dp = 100.dp
) {
    Canvas(
        modifier = Modifier
            .width(width = width)
            .aspectRatio(2f)
            .alpha(cloudAlpha)
    ) {
        drawCircle(
            color = color,
            radius = size.height * 0.35f,
            center = Offset(
                x = size.width * 0.25f,
                y = size.height * 0.65f
            )
        )
        drawCircle(
            color = color,
            radius = size.height * 0.5f,
            center = Offset(
                x = size.width * 0.5f,
                y = size.height * 0.5f
            )
        )
        drawCircle(
            color = color,
            radius = size.height * 0.25f,
            center = Offset(
                x = size.width * 0.75f,
                y = size.height * 0.75f
            )
        )
        drawRect(
            color = color,
            topLeft = Offset(
                x = size.width * 0.25f,
                y = size.height * 0.65f
            ),
            size = Size(
                width = size.width * 0.50f,
                height = size.height * 0.35f
            )
        )
    }
}