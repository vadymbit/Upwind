package com.vadym.upwind.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Cloud(
    width: Dp = 100.dp,
    color: Color
) {
    Canvas(
        modifier = Modifier
            .width(width = width)
            .aspectRatio(2f)
            .alpha(0.5f)
    ) {
        drawCircle(
            color = color,
            radius = size.height * 0.5f,
            center = Offset(
                x = size.width * 0.75f,
                y = size.height * 0.5f
            )
        )
        drawCircle(
            color = color,
            radius = size.height * 0.35f,
            center = Offset(
                x = size.width * 0.5f,
                y = size.height * 0.55f
            )
        )
    }
}