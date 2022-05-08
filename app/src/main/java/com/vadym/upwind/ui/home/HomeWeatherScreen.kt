package com.vadym.upwind.ui.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vadym.upwind.R
import com.vadym.upwind.model.CurrentWeatherModel
import com.vadym.upwind.model.DailyForecastModel
import com.vadym.upwind.model.HourlyForecastModel
import com.vadym.upwind.ui.home.components.*
import com.vadym.upwind.ui.theme.Blue200
import com.vadym.upwind.ui.theme.Blue700
import com.vadym.upwind.ui.theme.Pink200
import com.vadym.upwind.utils.resourcehelper.getWindSpeedByUnitType

@Composable
fun WeatherScreen(homeViewModel: HomeViewModel) {
    val weather by homeViewModel.weatherModel.collectAsState()
    Column {
        CurrentTemperatureBox(
            currentWeather = weather.currentWeatherModel,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        WeatherDetailBar(
            currentWeather = weather.currentWeatherModel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(2f)
        )
        SunStatus(
            sunrise = weather.currentWeatherModel.sunrise,
            sunset = weather.currentWeatherModel.sunset,
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        )
        ForecastBox(
            hourlyForecast = weather.hourlyWeatherModel,
            dailyForecast = weather.dailyWeatherModel,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(5f)
        )
    }
}

@Composable
fun CurrentTemperatureBox(
    currentWeather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = Modifier.drawBehind {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Blue200, Color.Transparent),
                    center = Offset(
                        x = size.width * 0.9f,
                        y = size.height * 0.4f
                    ),
                    radius = size.minDimension * 0.75f
                ),
                center = Offset(
                    x = size.width * 0.9f,
                    y = size.height * 0.4f
                ),
                radius = size.minDimension * 0.75f
            )
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(Blue200, Pink200),
                    start = Offset(
                        x = size.width * 0.9f - size.minDimension * 0.7f,
                        y = size.height * 0.4f - size.minDimension * 0.7f
                    ),
                    end = Offset(
                        x = size.width * 0.9f + size.minDimension * 0.7f,
                        y = size.height * 0.4f + size.minDimension * 0.7f
                    )
                ),
                center = Offset(
                    x = size.width * 0.9f,
                    y = size.height * 0.4f
                ),
                radius = size.minDimension * 0.6f
            )
        }
    ) {
        val infiniteTransaction = rememberInfiniteTransition()
        val bigCloudAnimation by infiniteTransaction.animateValue(
            initialValue = maxWidth,
            targetValue = -maxWidth,
            typeConverter = Dp.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 15000,
                    delayMillis = 300,
                    easing = LinearEasing
                )
            )
        )
        val smallCloudAnimation by infiniteTransaction.animateValue(
            initialValue = maxWidth,
            targetValue = -maxWidth,
            typeConverter = Dp.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 20000,
                    delayMillis = 1000,
                    easing = LinearEasing
                )
            )
        )
        Box(
            modifier = Modifier
                .offset(x = smallCloudAnimation)
                .align(Alignment.TopEnd)
        ) {
            Cloud(
                width = 100.dp,
                color = Blue700
            )
        }
        Box(
            modifier = Modifier
                .offset(bigCloudAnimation)
                .align(Alignment.Center)
        ) {
            BigCloud(
                width = 200.dp,
                color = Blue700
            )
        }
        Column(
            modifier = modifier
        ) {
            Text(
                text = currentWeather.city,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.temp, currentWeather.temperature),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = currentWeather.condition,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primaryVariant.copy(0.75f),
                        shape = CircleShape
                    )
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun ForecastBox(
    hourlyForecast: List<HourlyForecastModel>,
    dailyForecast: List<DailyForecastModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_today_forecast),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .alpha(0.5f)
        )
        HourlyForecast(
            weather = hourlyForecast,
            modifier = Modifier.weight(2f)
        )
        DailyForecast(
            dailyWeather = dailyForecast,
            modifier = Modifier.weight(3f)
        )
    }
}

@Composable
fun HourlyForecast(weather: List<HourlyForecastModel>, modifier: Modifier = Modifier) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        items(weather.size) {
            HourlyWeatherBox(
                modifier = Modifier.padding(
                    PaddingValues(
                        top = 4.dp,
                        bottom = 4.dp,
                        end = 24.dp
                    )
                ),
                dailyWeather = weather[it]
            )
        }
    }
}

@Composable
fun DailyForecast(dailyWeather: List<DailyForecastModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dailyWeather.size) {
            DailyWeatherBar(
                modifier = Modifier.fillMaxWidth(),
                dailyWeather = dailyWeather[it]
            )
        }
    }
}

@Composable
fun WeatherDetailBar(
    currentWeather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        WeatherDetailBox(
            icon = R.drawable.ic_drop,
            iconDescription = stringResource(id = R.string.icon_description_chance_of_rain),
            text = stringResource(id = R.string.pop, currentWeather.pop)
        )
        WeatherDetailBox(
            icon = R.drawable.ic_pressure,
            iconDescription = stringResource(id = R.string.icon_description_pressure),
            text = stringResource(id = R.string.pressure, currentWeather.pressure)
        )
        WeatherDetailBox(
            icon = R.drawable.ic_wind,
            iconDescription = stringResource(id = R.string.icon_description_wind_speed),
            text = getWindSpeedByUnitType(
                windSpeedUnitType = currentWeather.windSpeedUnitType,
                currentWeather.windSpeed
            )
        )
    }
}