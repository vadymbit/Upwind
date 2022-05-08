package com.vadym.upwind.ui.settings.components

import android.Manifest
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.vadym.upwind.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationFab(
    modifier: Modifier = Modifier,
    showSnackbar: suspend (String, SnackbarDuration) -> Unit,
    requestLocation: () -> Boolean
) {
    val scope = rememberCoroutineScope()
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION,
        onPermissionResult = {
            if (it) {
                val isLocationEnabled = requestLocation()
                if (!isLocationEnabled) {
                    scope.launch {
                        showSnackbar("Location service disabled. Please enable it", SnackbarDuration.Short)
                    }
                }
            } else {
                scope.launch {
                    showSnackbar("Can't get your location. Permissions denied", SnackbarDuration.Short)
                }
            }
        }
    )

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

