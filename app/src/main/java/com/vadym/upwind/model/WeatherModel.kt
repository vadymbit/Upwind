package com.vadym.upwind.model

data class WeatherModel(
    val currentWeatherModel: CurrentWeatherModel,
    val dailyWeatherModel: List<DailyForecastModel>,
    val hourlyWeatherModel: List<HourlyForecastModel>
)
