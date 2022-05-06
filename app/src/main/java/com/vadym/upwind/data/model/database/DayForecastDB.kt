package com.vadym.upwind.data.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["city_id", "date"])
data class DayForecastDB(
    val date: Long,
    @ColumnInfo(name = "city_id")
    val cityId: Int,
    @ColumnInfo(name = "max_temp_c")
    val maxTempC: Float,
    @ColumnInfo(name = "max_temp_f")
    val maxTempF: Float,
    @ColumnInfo(name = "min_temp_c")
    val minTempC: Float,
    @ColumnInfo(name = "min_temp_f")
    val minTempF: Float,
    @ColumnInfo(name = "condition_code")
    val conditionCode: Int
)
