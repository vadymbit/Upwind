package com.vadym.upwind.ui.settings.components

import android.Manifest
import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.vadym.upwind.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationFab(
    modifier: Modifier = Modifier,
    requestLocation: () -> Boolean
) {
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION,
        onPermissionResult = {
            if (it) {
                Log.d("Location", "Location disabled? please enable it")
            } else {
                Log.d("Location", "else")
            }
        }
    )
    fun checkPermissions() {
        when {
            locationPermissionState.status.isGranted -> {
                val isLocationEnabled = requestLocation()
                if (!isLocationEnabled) {
                    Log.d("Location", "Location disabled? please enable it")
                }
            }
            locationPermissionState.status.shouldShowRationale -> {
                Log.d("Location", "shouldShowRationale")
            }
            else -> {
                Log.d("Location", "else")
            }
        }
    }
    FloatingActionButton(
        onClick = {
            locationPermissionState.launchPermissionRequest()
        },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_my_location
            ),
            contentDescription = "Find My Location icon"
        )
    }
}

