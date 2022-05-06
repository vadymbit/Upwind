package com.vadym.upwind.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadym.upwind.data.model.database.CurrentWeatherDB
import com.vadym.upwind.data.model.database.DayForecastDB
import com.vadym.upwind.data.model.database.HourForecastDB

@Database(
    entities = [CurrentWeatherDB::class, DayForecastDB::class, HourForecastDB::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}