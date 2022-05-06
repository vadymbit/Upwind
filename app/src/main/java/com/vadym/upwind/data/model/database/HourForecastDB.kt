package com.vadym.upwind.data.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["city_id", "time"])
data class HourForecastDB(
    @ColumnInfo(name = "city_id")
    val cityId: Int,
    val time: Int,
    @ColumnInfo(name = "temp_c")
    val tempC: Float,
    @ColumnInfo(name = "temp_f")
    val tempF: Float,
    @ColumnInfo(name = "condition_code")
    val conditionCode: Int
)
