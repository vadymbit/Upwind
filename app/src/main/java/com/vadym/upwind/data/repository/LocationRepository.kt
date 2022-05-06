package com.vadym.upwind.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.util.Log
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
        prefsDataStore.setUserLocation(location.first, location.second)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getMyLocation() =
        suspendCancellableCoroutine<Pair<Double, Double>> { continuation ->
            val fusedLocationProvider = fusedLocationProviderClient.getCurrentLocation(
                LocationRequest.PRIORITY_LOW_POWER,
                cts.token
            )
            fusedLocationProvider.addOnSuccessListener { location ->
                continuation.resume(value = Pair(location.latitude, location.longitude), null)
            }
            fusedLocationProvider.addOnFailureListener { exception ->
                Log.d("LocationRepository", "Get location is failure")
                continuation.cancel(cause = exception)
            }
            continuation.invokeOnCancellation {
                Log.d(
                    "LocationRepository",
                    "Location coroutine is canceled ${it?.localizedMessage}"
                )
                cts.cancel()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getCurrentCity(): Flow<String> {
        val location = prefsDataStore.getLocation().filterNotNull()
        return location.mapLatest {
            if (it.first != null && it.second != null) {
                return@mapLatest getCities("${it.first},${it.second}").first().name
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