package com.vadym.upwind.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vadym.upwind.R
import com.vadym.upwind.model.HourlyForecastModel
import com.vadym.upwind.ui.theme.Blue200
import com.vadym.upwind.ui.theme.Pink200
import com.vadym.upwind.utils.vectorBrush

@Composable
fun HourlyWeatherBox(
    dailyWeather: HourlyForecastModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = dailyWeather.time,
            style = MaterialTheme.typography.body2
        )
        Icon(
            painter = painterResource(id = dailyWeather.condition),
            contentDescription = "Current weather at ${dailyWeather.time}",
            modifier = Modifier
                .size(36.dp)
                .padding(4.dp)
                .vectorBrush(
                    brush = Brush.linearGradient(
                        colors = listOf(Blue200, Pink200)
                    )
                )
        )
        Text(
            text = stringResource(id = R.string.temp, dailyWeather.temp),
            style = MaterialTheme.typography.h6
        )
    }
}