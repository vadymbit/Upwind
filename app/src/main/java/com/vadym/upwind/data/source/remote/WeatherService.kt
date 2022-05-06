package com.vadym.upwind.data.source.remote

import com.vadym.upwind.BuildConfig
import com.vadym.upwind.data.model.api.City
import com.vadym.upwind.data.model.api.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("q", encoded = true) location: String,
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("days") days: Int = 10,
        @Query("lang") lang: String = "en"
    ): Response<WeatherForecast>

    @GET("search.json")
    suspend fun findCitiesByLocation(
        @Query("q") location: String,
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): Response<List<City>>

    @GET("https://secure.geonames.org/countryCode?username=demos")
    suspend fun getCountryCode(
        @Query("lat") lat: String,
        @Query("lng") lng: String
    ): Response<String>
}