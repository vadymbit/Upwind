package com.vadym.upwind.ui.weatherlist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vadym.upwind.R
import com.vadym.upwind.model.WeatherListModel
import com.vadym.upwind.ui.theme.Blue200
import com.vadym.upwind.ui.theme.Pink200
import com.vadym.upwind.utils.resourcehelper.getWindSpeedByUnitType
import com.vadym.upwind.utils.vectorBrush

@Composable
fun WeatherBox(
    isCloseVisible: Boolean,
    onCloseClick: (Int) -> Unit,
    weatherModel: WeatherListModel,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.temp, weatherModel.temperature),
            modifier = Modifier.align(Alignment.TopStart),
            style = MaterialTheme.typography.h3

        )
        AnimatedVisibility(
            visible = !isCloseVisible,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(
                    id = weatherModel.condition
                ),
                contentDescription = "Image",
                tint = Color.Blue,
                modifier = Modifier
                    .size(60.dp)
                    .vectorBrush(
                        brush = Brush.linearGradient(
                            colors = listOf(Blue200, Pink200)
                        )
                    )
            )
        }
        AnimatedVisibility(
            visible = isCloseVisible,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            IconButton(
                onClick = { onCloseClick(weatherModel.cityId) }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete weather"
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 32.dp)
        ) {
            Text(
                text = weatherModel.city,
                style = MaterialTheme.typography.h6,
                maxLines = 2
            )
            Text(
                text = weatherModel.country,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                modifier = Modifier.alpha(0.5f)
            )
        }
        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_drop,
                ),
                contentDescription = "Image",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = stringResource(R.string.pop, weatherModel.pop),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_wind
                ),
                contentDescription = "Wind speed icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = getWindSpeedByUnitType(
                    windSpeedUnitType = weatherModel.windSpeedUnitType,
                    weatherModel.windSpeed
                ),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }

}