package com.vadym.upwind.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vadym.upwind.R
import com.vadym.upwind.ui.home.components.WeatherDetailBox
import com.vadym.upwind.ui.settings.components.LocationFab
import com.vadym.upwind.ui.settings.components.SettingsBar
import com.vadym.upwind.ui.theme.Blue200
import com.vadym.upwind.ui.theme.Pink200
import com.vadym.upwind.utils.resourcehelper.getTemperatureUnitTypeById
import com.vadym.upwind.utils.resourcehelper.getWindSpeedByUnitType
import com.vadym.upwind.utils.vectorBrush
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = getViewModel()) {
    val tempUnitType by viewModel.tempUnit.collectAsState()
    val windSpeedUnitType by viewModel.windSpeedUnit.collectAsState()
    val currentCity by viewModel.currentLocation.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        LocationBar()
        Text(
            text = currentCity,
            style = MaterialTheme.typography.h6
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_clear_sky_fill),
            contentDescription = "Sun icon",
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .padding(8.dp)
                .vectorBrush(
                    brush = Brush.linearGradient(
                        colors = listOf(Pink200, Blue200)
                    )
                )
        )
        Text(
            text = "Moonlight",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.75f),
                    shape = CircleShape
                )
                .padding(10.dp)
        )
        Text(
            text = getTemperatureUnitTypeById(
                tempUnitType = tempUnitType
            ),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
        )
        WeatherDetailBar(
            windSpeedUnitType,
            modifier = Modifier.fillMaxWidth()
        )
        SettingsBar(
            options = stringArrayResource(id = R.array.settings_temp_units),
            selectedOptionText = stringArrayResource(id = R.array.settings_temp_units)[tempUnitType],
            settingName = stringResource(id = R.string.settings_temperature),
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.setTempUnit(it) }
        )
        SettingsBar(
            options = stringArrayResource(id = R.array.settings_wind_speed_units),
            selectedOptionText = stringArrayResource(id = R.array.settings_wind_speed_units)[windSpeedUnitType],
            settingName = stringResource(id = R.string.settings_wind_speed),
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.setWindSpeedUnit(it) }
        )
        Spacer(modifier = Modifier.weight(1f))
        LocationFab(
            modifier = Modifier,
            requestLocation = { viewModel.requestUserLocation() }
        )
    }
}

@Composable
fun LocationBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_my_location),
            contentDescription = "My Location",
            modifier = Modifier
                .alpha(0.5f)
                .padding(end = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.user_location),
            modifier = Modifier
                .alpha(0.5f)
                .padding(8.dp)
        )
    }
}

@Composable
fun WeatherDetailBar(
    windSpeedUnitType: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        WeatherDetailBox(
            icon = R.drawable.ic_wind,
            iconDescription = "Wind speed",
            text = getWindSpeedByUnitType(
                windSpeedUnitType = windSpeedUnitType
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        WeatherDetailBox(
            icon = R.drawable.ic_drop,
            iconDescription = "Chance of rain",
            text = stringResource(id = R.string.pop, 13),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        WeatherDetailBox(
            icon = R.drawable.ic_pressure,
            iconDescription = "Pressure",
            text = stringResource(id = R.string.pressure, 1033f),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}