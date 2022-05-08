package com.vadym.upwind.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.vadym.upwind.data.model.api.City
import com.vadym.upwind.data.source.local.PrefsDataStore
import com.vadym.upwind.data.source.remote.WeatherService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationRepository(
    private val service: WeatherService,
    private val prefsDataStore: PrefsDataStore,
    private val context: Context
) {
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val cts = CancellationTokenSource()

    suspend fun saveUserLocation() {
        val location = getMyLocation()
        location?.let {
            prefsDataStore.setUserLocation(location.first, location.second)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getMyLocation() =
        suspendCancellableCoroutine<Pair<Double, Double>?> { continuation ->
            val currentLocation = fusedLocationProviderClient.getCurrentLocation(
                LocationRequest.PRIORITY_LOW_POWER,
                cts.token
            )
            currentLocation.addOnSuccessListener { location ->
                location?.let {
                    continuation.resume(value = Pair(location.latitude, location.longitude), null)
                }
            }
            currentLocation.addOnFailureListener { exception ->
                continuation.cancel(cause = exception)
            }
            continuation.invokeOnCancellation {
                cts.cancel()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getCurrentCity(): Flow<String> {
        val location = prefsDataStore.getLocation().filterNotNull()
        return location.mapLatest {
            if (it.first != null && it.second != null) {
                val cities = getCities("${it.first},${it.second}")
                if (cities.isNotEmpty()) return@mapLatest cities.first().name else ""
            } else {
                return@mapLatest null
            }
        }.filterNotNull()
    }

    suspend fun getCities(location: String): List<City> {
        val response = runCatching {
            service.findCitiesByLocation(location)
        }.getOrNull()
        if (response != null && response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }

    fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }
}