package com.vadym.upwind.di

import android.annotation.SuppressLint
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.vadym.upwind.data.repository.LocationRepository
import com.vadym.upwind.data.repository.SettingsRepository
import com.vadym.upwind.data.repository.WeatherRepository
import com.vadym.upwind.data.source.local.PrefsDataStore
import com.vadym.upwind.data.source.local.database.WeatherDatabase
import com.vadym.upwind.data.source.remote.WeatherService
import com.vadym.upwind.ui.weatherlist.WeatherListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.vadym.upwind.ui.home.HomeViewModel
import com.vadym.upwind.ui.settings.SettingsViewModel
import com.vadym.upwind.utils.Const
import org.koin.android.ext.koin.androidApplication
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
@SuppressLint("VisibleForTests")

val dataModule = module {
    single<WeatherService> {
        Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
            .create(WeatherService::class.java)
    }
    single {
        Room.databaseBuilder(
            androidApplication(),
            WeatherDatabase::class.java,
            "weather-db"
        ).build()
            .weatherDao()
    }
    singleOf(::PrefsDataStore)
    singleOf(::SettingsRepository)
    singleOf(::WeatherRepository)
    singleOf(::LocationRepository)
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::WeatherListViewModel)
    viewModelOf(::SettingsViewModel)
}

