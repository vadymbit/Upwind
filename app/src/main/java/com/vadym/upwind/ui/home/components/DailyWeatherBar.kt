package com.vadym.upwind.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vadym.upwind.R
import com.vadym.upwind.model.DailyForecastModel
import com.vadym.upwind.ui.theme.Blue200
import com.vadym.upwind.ui.theme.Pink200
import com.vadym.upwind.utils.vectorBrush

@Composable
fun DailyWeatherBar(dailyWeather: DailyForecastModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = dailyWeather.dayOfWeek,
            modifier.align(Alignment.CenterStart)
        )
        Icon(
            painter = painterResource(id = dailyWeather.condition),
            contentDescription = "Weather fo ${dailyWeather.dayOfWeek}",
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
                .align(Alignment.Center)
                .vectorBrush(
                    brush = Brush.linearGradient(
                        colors = listOf(Blue200, Pink200)
                    )
                )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(60.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            ) {
                Text(text = stringResource(id = R.string.temp, dailyWeather.tempDay))
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.temp, dailyWeather.tempNight),
                    modifier = Modifier.alpha(0.5f)
                )
            }
        }
    }
}